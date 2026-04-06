
public class Librarian {
    private String employeeID;
    private String name;
    private int age;
    private int size =5;
    private Book[] arr;

    public Librarian(String employeeID, String name, int age, int size) {
        this.employeeID = employeeID;
        this.name = name;
        this.age = age;
        this.size = size;
        this.arr = new Book[5];
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Book[] getArr() {
        return arr;
    }

    public void setArr(Book[] arr) {
        this.arr = arr;
    }
    public void addBook(Book book){
        for(int i=0;i<5;i++){
            if(arr[i]==null){
                arr[i]=book;
                break;
            }
            if(i==4){
                //HATA MESAJI KİTAP EKLENEMEDİ YER YOK!!!
            }
        }
    }
    public void removeBook(String isbn){
        for(int i=0;i<5;i++){
            if(arr[i] != null && arr[i].getIsbn().equals(isbn)){
                arr[i]=null;
            }
        }
    }
    public Book findbook(String isbn){
        for(int i=0;i<5;i++){
            if (arr[i] != null && arr[i].getIsbn().equals(isbn)){
                return arr[i];
            }
        }
        return null;    // HATA KİTAP BULUNAMADI
    }
    public void updatePrice(Book book, double newPrice){
        book.setPrice(newPrice);
    }
    public void findBookAndApplyDiscount(String isbn, double discountPercentage){
        for(int i=0;i<5;i++){
            if (arr[i] != null && arr[i].getIsbn().equals(isbn)){
                arr[i].setPrice(arr[i].getPrice()-arr[i].getPrice()*discountPercentage/100);
            }
        }
    }
    public void displayAllBooks(){
        for(int i=0;i<5;i++){
            if (arr[i] != null){
                System.out.println("The informations about book number "+ (i+1));
                System.out.println(arr[i].getTitle()+" is "+arr[i].getPrice()+" dolar and its "+arr[i].getPageCount()+" pages. Books isbn is : "+arr[i].getIsbn());
            }
        }
    }















}
















