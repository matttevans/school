package cis233.a1;

/*
    Matt Evans
 */

public class LinkedList<AnyType extends Comparable<? super AnyType>> {

    private ListNode<AnyType> header;
    private static int count;


    public LinkedList() {

    }

    public boolean isEmpty() {

        return header.next == null;
    }

    public void makeEmpty() {

        header.next = null;
    }

    public ListIterator<AnyType> zeroth() {

        return new ListIterator<AnyType>(header);
    }

    public ListIterator<AnyType> first() {

        return new ListIterator<AnyType>(header.next);
    }

    private void insert( AnyType x, ListIterator<AnyType> p ) {

        if( p != null && p.current != null )

            p.current.next = new ListNode<AnyType>( x, p.current.next );
    }

    public ListIterator<AnyType> find(AnyType x ) {

        ListNode<AnyType> itr = header.next;

        while( itr != null && !itr.element.equals( x ) )
            itr = itr.next;

        return new ListIterator<AnyType>( itr );
    }

    public ListIterator<AnyType> findPrevious(AnyType x ) {

        ListNode<AnyType> itr = header;

        while( itr.next != null && !itr.next.element.equals( x ) )
            itr = itr.next;

        return new ListIterator<AnyType>( itr );
    }

    public void remove( AnyType x ) {

        ListIterator<AnyType> p = findPrevious( x );

        if( p.current.next != null )
            p.current.next = p.current.next.next;  // Bypass deleted node
    }

    public void add(AnyType newElement) {

        ListNode<AnyType> current;
        ListNode<AnyType> newNode = new ListNode<>(newElement);

        if (header == null || header.element.compareTo(newElement) >= 0) {

            newNode.next = header;
            header = newNode;
        }
        else {

            current = header;

            while (current.next != null && current.next.element.compareTo(newElement) < 0) {

                current = current.next;
            }

            newNode.next = current.next;
            current.next = newNode;
        }
    }

    public boolean replace(AnyType element, AnyType newElement) {

        ListNode<AnyType> current = header;

        if(header == null) {

            System.out.println("The list is currently empty.");
        }

        while(current.next != null) {

            if(current.element.compareTo(element) == 0) {

                add(newElement);
                remove(current.element);

                return true;
            }

            current = current.next;
        }

        return false;
    }

    public void showList() {

        int size = 0;

        ListNode temp = header;

        if(header == null) {

            System.out.println("The list is currently empty.");
        }

        while (temp != null) {

            System.out.print(temp.element+"\n");

            temp = temp.next;
            size++;
        }

        System.out.println("There is " + size + " items in this list");
    }

    public void showList(int numPerLine) {

        ListNode temp = header;

        int count = 0;
        int size = 0;

        if(header == null) {

            System.out.println("\nThe list is currently empty.");
        }

        while (temp != null) {

            if(count == numPerLine) {

                System.out.println();
                count = 0;
            }

            System.out.print(temp.element + " ");

            count++;
            size++;

            temp = temp.next;
        }

        System.out.println("\nThere is " + size + " items in this list");
    }

    public Result getMode() {

        Result result = new Result() {

            public AnyType mode() {

                ListNode<AnyType> current = header;

                ListNode<AnyType> temp = header;

                AnyType mode = header.element;

                int maxCount = 1;

                while(current.next != null) {

                    AnyType value = current.element;
                    int tempCount = 0;

                    while(temp.next != null) {

                        if(temp.element.compareTo(value) == 0) {

                            tempCount++;
                        }
                        if(tempCount > maxCount) {

                            maxCount = tempCount;
                            mode = value;
                        }
                        temp = temp.next;
                    }

                    current = current.next;
                    temp = header;
                }
                count = maxCount;

                return mode;
            }

            public int count() {

                return count;
            }
        };

        return result;
    }

    public static <AnyType extends Comparable<? super AnyType>>
        void printList(LinkedList<AnyType> theList) {

        if( theList.isEmpty( ) )
            System.out.print( "Empty list" );
        else {

            ListIterator<AnyType> itr = theList.first( );
            for( ; itr.isValid( ); itr.advance( ) )
                System.out.print( itr.retrieve( ) + " " );
        }

        System.out.println( );
    }

    public static <AnyType extends Comparable<? super AnyType>>
        int listSize(LinkedList<AnyType> theList) {

        ListIterator<AnyType> itr;
        int size = 0;

        for( itr = theList.first(); itr.isValid(); itr.advance() )
            size++;

        return size;
    }

} 