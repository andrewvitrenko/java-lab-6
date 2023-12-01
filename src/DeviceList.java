import java.util.*;

public class DeviceList implements List<Device> {

    private static final int DEFAULT_CAPACITY = 15;
    private Device[] elements;
    private int size;


    public DeviceList() {
        this.elements = new Device[DeviceList.DEFAULT_CAPACITY];
        this.size = 0;
    }

    public DeviceList(Device firstItem) {
        this.elements = new Device[DeviceList.DEFAULT_CAPACITY];
        this.size = 0;
        this.add(firstItem);
    }

    public DeviceList(Collection<Device> c) {
        this.elements = new Device[DeviceList.DEFAULT_CAPACITY];
        this.size = 0;
        this.addAll(c);
    }

    /**
     * Returns number of elements in list
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Checks if there is no elements in list
     */
    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Checks if list contains element
     */
    @Override
    public boolean contains(Object obj) {
        for (Device element : this.elements) {
            if (Objects.equals(element, obj)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Clears the list
     */
    @Override
    public void clear() {
        this.elements = new Device[DeviceList.DEFAULT_CAPACITY];
        this.size = 0;
    }

    /**
     * Finds index of first occurrence of the element in list. Returns -1 if not found
     */
    @Override
    public int indexOf(Object obj) {
        for (int i = 0; i < this.size; i++) {
            Device element = this.elements[i];

            if (Objects.equals(element, obj)) return i;
        }

        return -1;
    }

    /**
     * Finds the index of last occurrence of specified element in the list. Returns -1 if not found
     */
    @Override
    public int lastIndexOf(Object obj) {
        for (int i = this.size - 1; i >= 0; i--) {
            Device element = this.elements[i];

            if (Objects.equals(element, obj)) return i;
        }

        return -1;
    }

    /**
     * Sorts lists by defined comparator
     */
    @Override
    public void sort(Comparator<? super Device> c) {
        Arrays.sort(this.elements, c);
    }

    /**
     * Returns element from list by given index
     */
    @Override
    public Device get(int index) {
        this.checkIndexBounds(index, true);

        return this.elements[index];
    }

    /**
     * Adds element to the end of array
     */
    @Override
    public boolean add(Device element) {
        this.ensureCapacity(this.size + 1);
        this.elements[this.size] = element;
        this.size++;
        return true;
    }

    /**
     * Adds element in specific position in the list
     */
    @Override
    public void add(int index, Device element) {
        this.checkIndexBounds(index, false);

        this.ensureCapacity(this.size + 1);
        // Copying part of the array after index creates free gap for element to insert
        System.arraycopy(this.elements, index, this.elements, index + 1, this.size - index);
        this.elements[index] = element;
        this.size++;
    }

    /**
     * Adds all items from collection to list on specified position
     */
    @Override
    public boolean addAll(int index, Collection<? extends Device> c) {
        this.checkIndexBounds(index, false);

        Device[] newElements = new Device[this.size + c.size()];
        //Copy `elements` array to `newElements` from 0 to `index` element
        System.arraycopy(this.elements, 0, newElements, 0, index);

        // Insert collection from props
        int newIndex = index;
        for (Device element : c) {
            newElements[newIndex++] = element;
        }

        // Copy the rest of elements into new array
        System.arraycopy(this.elements, index, newElements, newIndex, this.size - index);

        this.elements = newElements;
        this.size += c.size();
        return !c.isEmpty();
    }

    /**
     * Adds elements from collection in the end of list
     */
    @Override
    public boolean addAll(Collection<? extends Device> c) {
        for (Device item : c) {
            this.add(item);
        }

        return !this.isEmpty();
    }

    /**
     * Sets elements in specified position
     */
    @Override
    public Device set(int index, Device element) {
        this.checkIndexBounds(index, true);

        Device oldValue = this.elements[index];
        this.elements[index] = element;
        return oldValue;
    }

    /**
     * Removes element on index
     */
    @Override
    public Device remove(int index) {
        this.checkIndexBounds(index, true);

        Device oldValue = this.elements[index];
        // Removed element creates gap if it is placed in the middle of list, so we need to move items after it
        int numMoved = this.size - index - 1;
        if (numMoved > 0) {
            // Copying elements after index will move elements to the left to fill the gap
            System.arraycopy(this.elements, index + 1, this.elements, index, numMoved);
        }
        this.elements[--this.size] = null; // Remove last element of list, we have moved it to the left
        return oldValue;
    }

    /**
     * Removes element from list
     */
    @Override
    public boolean remove(Object obj) {
        for (int i = 0; i < this.size; i++) {
            if (Objects.equals(this.elements[i], obj)) {
                this.remove(i);
                return true;
            }
        }

        return false;
    }

    /**
     * Removes from list all elements that are in collection
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        boolean modified = false;

        for (Object obj : c) {
            boolean contains = this.contains(obj);
            if (!contains) continue;


            this.remove(obj);
            modified = true;
        }

        return modified;
    }

    /**
     * Checks if list contains all items from given collection
     */
    @Override
    public boolean containsAll(Collection<?> collection) {
        for (Object item : collection) {
            if (!this.contains(item)) return false;
        }

        return true;
    }

    /**
     * Checks if objects are equal
     */
    @Override
    public boolean equals(Object obj) {
        // Checks the reference
        if (this == obj) {
            return true;
        }
        if (obj instanceof DeviceList) {
            DeviceList other = (DeviceList) obj;
            // Checks size
            if (this.size() != other.size()) {
                return false;
            }
            Iterator<Device> iterator1 = this.iterator();
            Iterator<?> iterator2 = other.iterator();
            // Cycling through list to compare elements
            while (iterator1.hasNext()) {
                Device elem1 = iterator1.next();
                Object elem2 = iterator2.next();

                // Compares items
                if (!(Objects.equals(elem1, elem2))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Retains only elements in the list, that are also in given collection
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        boolean modified = false;

        for (Device elem : this.elements) {
            if (c.contains(elem)) continue;

            this.remove(elem);
            modified = true;
        }

        return modified;
    }

    /**
     * Returns the hashCode of the list
     */
    @Override
    public int hashCode() {
        int result = 1;
        for (Object element : this.elements) {
            result = 31 * result + (element == null ? 0 : element.hashCode());
        }
        return result;
    }

    /**
     * Transforms list into Object[]
     */
    @Override
    public Object[] toArray() {
        Object[] result = new Object[this.size];

        System.arraycopy(this.elements, 0, result, 0, this.size);

        return result;
    }

    /**
     * Transforms list into typed array
     */
    @Override
    public <T> T[] toArray(T[] array) {
        if (array.length < this.size) {
            array = Arrays.copyOf(array, this.size);
        }

        System.arraycopy(this.elements, 0, array, 0, this.size);

        if (array.length > this.size) {
            array[this.size] = null;
        }

        return array;
    }

    /**
     * Returns iterator for the list
     */
    @Override
    public Iterator<Device> iterator() {
        return new Iterator<>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            public Device next() {
                return elements[currentIndex++];
            }
        };
    }

    /**
     * Returns custom list iterator with starting index 0
     */
    @Override
    public ListIterator<Device> listIterator() {
        return new CustomListIterator(0);
    }

    /**
     * Returns custom list iterator with defined starting index
     */
    @Override
    public ListIterator<Device> listIterator(int index) {
        this.checkIndexBounds(index, true);

        return new CustomListIterator(index);
    }

    /**
     * Extracts array elements in defined range
     */
    @Override
    public List<Device> subList(int start, int end) {
        this.checkIndexBounds(start, true);
        this.checkIndexBounds(end, false);

        DeviceList result = new DeviceList();

        result.addAll(Arrays.asList(this.elements).subList(start, end));

        return result;
    }

    // ----- Utils -----

    /**
     * Checks if array is big enough
     */
    private void ensureCapacity(int minCapacity) {
        int currentCapacity = this.elements.length;
        if (minCapacity > currentCapacity) {
            // Calculate new array capacity
            int newCapacity = (int) Math.ceil(currentCapacity * 1.3);
            // Copy elements to new array with new length
            this.elements = Arrays.copyOf(this.elements, newCapacity);
        }
    }

    /**
     * Checks if index is valid
     *
     * @throws IndexOutOfBoundsException if index is invalid
     */
    private void checkIndexBounds(int index, boolean strict) {
        boolean isBiggerThanSize = strict ? index >= this.size : index > this.size;

        if (index < 0 || isBiggerThanSize) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size);
        }
    }

    /**
     * Custom list iterator class
     */
    private class CustomListIterator implements ListIterator<Device> {
        private int currentIndex;
        private int lastReturnedIndex = -1;

        public CustomListIterator(int index) {
            this.currentIndex = index;
        }

        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        @Override
        public Device next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            lastReturnedIndex = currentIndex;
            return elements[currentIndex++];
        }

        @Override
        public boolean hasPrevious() {
            return currentIndex > 0;
        }

        @Override
        public Device previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            lastReturnedIndex = --currentIndex;
            return elements[currentIndex];
        }

        @Override
        public int nextIndex() {
            return currentIndex;
        }

        @Override
        public int previousIndex() {
            return currentIndex - 1;
        }

        @Override
        public void remove() {
            if (lastReturnedIndex == -1) {
                throw new IllegalStateException();
            }
            DeviceList.this.remove(lastReturnedIndex);
            currentIndex = lastReturnedIndex;
            lastReturnedIndex = -1;
        }

        @Override
        public void set(Device element) {
            if (lastReturnedIndex == -1) {
                throw new IllegalStateException();
            }
            DeviceList.this.set(lastReturnedIndex, element);
        }

        @Override
        public void add(Device element) {
            DeviceList.this.add(currentIndex, element);
            currentIndex++;
            lastReturnedIndex = -1;
        }
    }
}
