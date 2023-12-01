import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class DeviceListTest {

    @Test
    void testConstructorWithItem() {
        DeviceList deviceList = new DeviceList(new Microwave(750, 9000));

        assertEquals(deviceList.size(), 1);
        assertTrue(deviceList.contains(new Microwave(750, 9000)));
    }

    @Test
    void testConstructorWithCollection() {
        Device device1 = new Iron(1000, 2500);
        Device device2 = new Oven(10000, 5000);

        DeviceList deviceList = new DeviceList(List.of(device1, device2));

        assertTrue(deviceList.contains(device1));
        assertTrue(deviceList.contains(device2));
        assertEquals(deviceList.size(), 2);
    }

    @Test
    void testAddAndGet() {
        DeviceList deviceList = new DeviceList();
        Device device1 = new Iron(1000, 2500);
        Device device2 = new Oven(10000, 5000);

        deviceList.add(device1);
        deviceList.add(device2);

        assertEquals(device1, deviceList.get(0));
        assertEquals(device2, deviceList.get(1));
    }

    @Test
    void testAddAll() {
        DeviceList deviceList = new DeviceList();
        Device device1 = new Iron(1000, 2500);
        Device device2 = new Oven(10000, 5000);

        List<Device> devicesToAdd = List.of(device1, device2);
        assertTrue(deviceList.addAll(devicesToAdd));

        assertEquals(2, deviceList.size());
        assertEquals(device1, deviceList.get(0));
        assertEquals(device2, deviceList.get(1));
    }

    @Test
    void testAddAllWithIndex() {
        Device device1 = new Iron(1000, 2500);
        Device device2 = new Oven(10000, 5000);

        List<Device> devicesToAdd = List.of(device1, device2);
        DeviceList deviceList = new DeviceList(devicesToAdd);

        Device device3 = new Microwave(750, 9000);
        Device device4 = new Iron(2000, 2500);
        Device device5 = new Oven(10500, 5000);

        List<Device> newDevices = List.of(device3, device4, device5);
        deviceList.addAll(1, newDevices);

        assertEquals(5, deviceList.size());
        assertEquals(device3, deviceList.get(1));
        assertEquals(device4, deviceList.get(2));
        assertEquals(device5, deviceList.get(3));
        assertEquals(device2, deviceList.get(4));

    }

    @Test
    void testRemove() {
        DeviceList deviceList = new DeviceList();
        Device device1 = new Iron(1000, 2500);
        Device device2 = new Oven(10000, 5000);

        deviceList.add(device1);
        deviceList.add(device2);

        deviceList.remove(0);
        assertFalse(deviceList.contains(device1));
        assertEquals(1, deviceList.size());
        assertEquals(device2, deviceList.get(0));
    }

    @Test
    void testRemoveObj() {
        DeviceList deviceList = new DeviceList();
        Device device1 = new Iron(1000, 2500);
        Device device2 = new Oven(10000, 5000);

        deviceList.add(device1);
        deviceList.add(device2);

        assertTrue(deviceList.remove(device1));
        assertFalse(deviceList.contains(device1));
        assertEquals(1, deviceList.size());
        assertEquals(device2, deviceList.get(0));
    }

    @Test
    void testRemoveAll() {
        DeviceList deviceList = new DeviceList();
        Device device1 = new Iron(1000, 2500);
        Device device2 = new Oven(10000, 5000);
        Device device3 = new Microwave(750, 9000);

        deviceList.addAll(List.of(device1, device2, device3));

        List<Device> devicesToRemove = List.of(device1, device2);
        assertTrue(deviceList.removeAll(devicesToRemove));

        assertEquals(1, deviceList.size());
        assertEquals(device3, deviceList.get(0));
        assertFalse(deviceList.contains(device1));
        assertFalse(deviceList.contains(device2));
        assertTrue(deviceList.contains(device3));
    }

    @Test
    void testRetainAll() {
        DeviceList deviceList = new DeviceList();
        Device device1 = new Iron(1000, 2500);
        Device device2 = new Oven(10000, 5000);
        Device device3 = new Microwave(750, 9000);

        deviceList.addAll(List.of(device1, device2, device3));

        List<Device> devicesToRetain = Arrays.asList(device2, device3);
        assertTrue(deviceList.retainAll(devicesToRetain));

        assertEquals(2, deviceList.size());
        assertEquals(device2, deviceList.get(0));
        assertEquals(device3, deviceList.get(1));
        assertFalse(deviceList.contains(device1));
    }

    @Test
    void testSort() {
        DeviceList deviceList = new DeviceList();
        Device device1 = new Iron(1000, 2500);
        Device device2 = new Oven(10000, 5000);
        Device device3 = new Microwave(750, 9000);

        deviceList.addAll(List.of(device1, device2, device3));

        deviceList.sort(new SortByPower());

        assertEquals(device3, deviceList.get(0));
        assertEquals(device1, deviceList.get(1));
        assertEquals(device2, deviceList.get(2));
    }

    @Test
    void testSubList() {
        DeviceList deviceList = new DeviceList();
        Device device1 = new Iron(1000, 2500);
        Device device2 = new Oven(10000, 5000);
        Device device3 = new Microwave(750, 9000);

        deviceList.addAll(List.of(device1, device2, device3));

        List<Device> subList = deviceList.subList(1, 3);

        assertEquals(2, subList.size());
        assertEquals(device2, subList.get(0));
        assertEquals(device3, subList.get(1));
    }

    @Test
    void testEquals() {
        DeviceList deviceList1 = new DeviceList();
        DeviceList deviceList2 = new DeviceList();

        Device device1 = new Iron(1000, 2500);
        Device device2 = new Oven(10000, 5000);

        deviceList1.addAll(List.of(device1, device2));
        deviceList2.addAll(List.of(device1, device2));

        // Comparison by elements and iterators, size and elements
        assertEquals(deviceList1, deviceList2);
        // Comparison by reference
        assertEquals(deviceList1, deviceList1);

        // Comparison by sizes
        deviceList2.add(new Microwave(750, 9000));
        assertNotEquals(deviceList1, deviceList2);
        // Comparison by elements
        deviceList2.remove(1);
        assertNotEquals(deviceList1, deviceList2);

        // Comparison by class instantiation
        assertNotEquals(deviceList1, new Object());

    }

    @Test
    void testClear() {
        DeviceList deviceList = new DeviceList();
        Device device1 = new Iron(1000, 2500);
        Device device2 = new Oven(10000, 5000);

        deviceList.addAll(List.of(device1, device2));

        deviceList.clear();

        assertTrue(deviceList.isEmpty());
    }

    @Test
    void testContains() {
        DeviceList deviceList = new DeviceList();
        Device device1 = new Iron(1000, 2500);
        Device device2 = new Oven(10000, 5000);

        deviceList.addAll(List.of(device1, device2));

        assertTrue(deviceList.contains(device1));
        assertFalse(deviceList.contains(new Microwave(750, 9000)));
    }

    @Test
    void testContainsAll() {
        DeviceList deviceList = new DeviceList();
        Device device1 = new Iron(1000, 2500);
        Device device2 = new Oven(10000, 5000);

        List<Device> devices = List.of(device1, device2);

        deviceList.addAll(devices);

        assertTrue(deviceList.containsAll(devices));

        Device device3 = new Microwave(750, 9000);
        assertFalse(deviceList.containsAll(List.of(device1, device3)));
    }

    @Test
    void testIndexOf() {
        DeviceList deviceList = new DeviceList();
        Device device1 = new Iron(1000, 2500);
        Device device2 = new Oven(10000, 5000);

        deviceList.addAll(List.of(device1, device2));

        assertEquals(0, deviceList.indexOf(device1));
        assertEquals(1, deviceList.indexOf(device2));
        assertEquals(-1, deviceList.indexOf(new Microwave(750, 9000)));
    }

    @Test
    void testLastIndexOf() {
        DeviceList deviceList = new DeviceList();
        Device device1 = new Iron(1000, 2500);
        Device device2 = new Oven(10000, 5000);

        deviceList.addAll(List.of(device1, device2, device1));

        assertEquals(2, deviceList.lastIndexOf(device1));
        assertEquals(1, deviceList.lastIndexOf(device2));
        assertEquals(-1, deviceList.lastIndexOf(new Microwave(750, 9000)));
    }

    @Test
    void testSet() {
        DeviceList deviceList = new DeviceList();
        Device device1 = new Iron(1000, 2500);
        Device device2 = new Oven(10000, 5000);

        deviceList.addAll(List.of(device1, device2));

        Device newDevice = new Microwave(750, 9000);
        Device oldDevice = deviceList.set(1, newDevice);

        assertEquals(device2, oldDevice);
        assertEquals(newDevice, deviceList.get(1));
    }

    @Test
    void testHashCode() {
        Device device1 = new Iron(1000, 2500);
        Device device2 = new Oven(10000, 5000);

        DeviceList deviceList1 = new DeviceList(List.of(device1, device2));
        DeviceList deviceList2 = new DeviceList(List.of(device1, device2));

        assertEquals(deviceList1.hashCode(), deviceList2.hashCode());
    }

    @Test
    void testToArray() {
        DeviceList deviceList = new DeviceList();
        Device device1 = new Iron(1000, 2500);
        Device device2 = new Oven(10000, 5000);

        deviceList.addAll(List.of(device1, device2));

        Object[] array = deviceList.toArray();

        assertEquals(2, array.length);
        assertEquals(device1, array[0]);
        assertEquals(device2, array[1]);
    }

    @Test
    void testToArrayWithGenericArray() {
        DeviceList deviceList = new DeviceList();
        Device device1 = new Iron(1000, 2500);
        Device device2 = new Oven(10000, 5000);

        deviceList.addAll(List.of(device1, device2));

        Device[] array = deviceList.toArray(new Device[0]);

        assertEquals(2, array.length);
        assertEquals(device1, array[0]);
        assertEquals(device2, array[1]);
    }

    @Test
    void testToArrayWithBiggerArray() {
        Device device1 = new Iron(1000, 2500);
        Device device2 = new Oven(10000, 5000);
        DeviceList deviceList = new DeviceList(List.of(device1, device2));

        Device[] array = new Device[30];
        deviceList.toArray(array);

        assertNull(array[deviceList.size()]);
    }

    // ----- Utils -----

    @Test
    void testEnsureCapacity() {
        DeviceList deviceList = new DeviceList();

        Device[] devices = new Device[30];

        deviceList.addAll(Arrays.asList(devices));
        assertEquals(30, deviceList.size());
    }

    @Test
    void testCheckIndexBounds() {
        DeviceList deviceList = new DeviceList();
        assertThrows(IndexOutOfBoundsException.class, () -> deviceList.get(0));
    }
    
    // ----- Iterators -----

    @Test
    void testListIterator() {
        DeviceList deviceList = new DeviceList();
        Device device1 = new Iron(1000, 2500);
        Device device2 = new Oven(10000, 5000);

        deviceList.addAll(List.of(device1, device2));

        List<Device> result = new ArrayList<>();
        ListIterator<Device> listIterator = deviceList.listIterator();

        // Check that iterator cycles through list correctly
        while (listIterator.hasNext()) {
            result.add(listIterator.next());
        }

        assertEquals(List.of(device1, device2), result);
    }

    @Test
    void testListIteratorWithIndex() {
        DeviceList deviceList = new DeviceList();
        Device device1 = new Iron(1000, 2500);
        Device device2 = new Oven(10000, 5000);

        deviceList.addAll(List.of(device1, device2));

        List<Device> result = new ArrayList<>();
        ListIterator<Device> listIterator = deviceList.listIterator(1);

        while (listIterator.hasNext()) {
            result.add(listIterator.next());
        }

        assertEquals(List.of(device2), result);
    }

    @Test
    void testListIteratorNextAndHasNext() {
        DeviceList deviceList = new DeviceList();
        Device device1 = new Iron(1000, 2500);
        Device device2 = new Oven(10000, 5000);

        deviceList.addAll(List.of(device1, device2));
        ListIterator<Device> listIterator = deviceList.listIterator();

        assertTrue(listIterator.hasNext());
        assertEquals(device1, listIterator.next());
        assertTrue(listIterator.hasNext());
        assertEquals(device2, listIterator.next());
        assertFalse(listIterator.hasNext());
    }

    @Test
    void testListIteratorPreviousAndHasPrevious() {
        DeviceList deviceList = new DeviceList();
        Device device1 = new Iron(1000, 2500);
        Device device2 = new Oven(10000, 5000);

        deviceList.addAll(List.of(device1, device2));
        ListIterator<Device> listIterator = deviceList.listIterator(1);

        assertTrue(listIterator.hasPrevious());
        assertEquals(device1, listIterator.previous());
        assertFalse(listIterator.hasPrevious());
    }

    @Test
    void testListIteratorNextIndex() {
        DeviceList deviceList = new DeviceList();
        Device device1 = new Iron(1000, 2500);
        Device device2 = new Oven(10000, 5000);

        deviceList.addAll(List.of(device1, device2));
        ListIterator<Device> listIterator = deviceList.listIterator();

        assertEquals(0, listIterator.nextIndex());
        listIterator.next();
        assertEquals(1, listIterator.nextIndex());
    }

    @Test
    void testListIteratorPreviousIndex() {
        DeviceList deviceList = new DeviceList();
        Device device1 = new Iron(1000, 2500);
        Device device2 = new Oven(10000, 5000);
        Device device3 = new Microwave(750, 9000);

        deviceList.addAll(List.of(device1, device2, device3));
        ListIterator<Device> listIterator = deviceList.listIterator(2);

        assertEquals(1, listIterator.previousIndex());
        listIterator.previous();
        assertEquals(0, listIterator.previousIndex());
    }

    @Test
    void testListIteratorRemove() {
        DeviceList deviceList = new DeviceList();
        Device device1 = new Iron(1000, 2500);
        Device device2 = new Oven(10000, 5000);

        deviceList.addAll(List.of(device1, device2));
        ListIterator<Device> listIterator = deviceList.listIterator();

        listIterator.next();
        listIterator.remove();

        assertEquals(1, deviceList.size());
        assertFalse(deviceList.contains(device1));
    }

    @Test
    void testListIteratorSet() {
        DeviceList deviceList = new DeviceList();
        Device device1 = new Iron(1000, 2500);
        Device device2 = new Oven(10000, 5000);
        Device device3 = new Microwave(750, 9000);

        deviceList.addAll(List.of(device1, device2));
        ListIterator<Device> listIterator = deviceList.listIterator();

        // Replace first element with device3
        listIterator.next();
        listIterator.set(device3);

        assertFalse(deviceList.contains(device1));
        assertEquals(device3, deviceList.get(0));
    }

    @Test
    void testListIteratorAdd() {
        DeviceList deviceList = new DeviceList();
        Device device1 = new Iron(1000, 2500);
        Device device2 = new Oven(10000, 5000);
        Device device3 = new Microwave(750, 9000);

        deviceList.addAll(List.of(device1, device2));
        ListIterator<Device> iterator = deviceList.listIterator();

        // Adds element into the beginning of array
        // this method is supposed to add element to the list inside cycle
        // so it adds element by current index
        // which is zero at the beginning - now
        iterator.add(device3);

        assertTrue(deviceList.contains(device3));
        assertEquals(device3, deviceList.get(0));
        assertEquals(3, deviceList.size());
    }

    @Test
    void testListIteratorRemoveInEmptyList() {
        DeviceList deviceList = new DeviceList();
        ListIterator<Device> listIterator = deviceList.listIterator();
        assertThrows(IllegalStateException.class, () -> listIterator.remove());
    }

    @Test
    void testListIteratorSetWithoutNext() {
        DeviceList deviceList = new DeviceList();
        ListIterator<Device> listIterator = deviceList.listIterator();
        assertThrows(IllegalStateException.class, () -> listIterator.set(new Microwave(750, 9000)));
    }

    @Test
    void testListIteratorNextInEmptyList() {
        DeviceList deviceList = new DeviceList();
        ListIterator<Device> listIterator = deviceList.listIterator();

        assertThrows(NoSuchElementException.class, () -> listIterator.next());
    }

    @Test
    void testPreviousInEmptyList() {
        DeviceList deviceList = new DeviceList();
        ListIterator<Device> listIterator = deviceList.listIterator();

        assertThrows(NoSuchElementException.class, () -> listIterator.previous());
    }

    @Test
    void testListIteratorAddInTheEndOfList() {
        DeviceList deviceList = new DeviceList();
        ListIterator<Device> listIterator = deviceList.listIterator();
        Device device = new Microwave(750, 9000);

        // Adding without calling next() should be allowed
        // it will add element to the end of list
        listIterator.add(device);

        assertTrue(deviceList.contains(device));
        assertEquals(1, deviceList.size());
    }
}
