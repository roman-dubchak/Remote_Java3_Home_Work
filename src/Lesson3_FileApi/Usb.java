package Lesson3_FileApi;

public interface Usb {
    void connectWithUsbCable();
}

class MemoryCard {
    public void insert() {
        System.out.println("Cart in");
    }

    public void copyData(){
        System.out.println("Data is copy");
    }
}

class CardReader implements Usb {
    private MemoryCard memoryCard;

    public CardReader(MemoryCard memoryCard) {
        this.memoryCard = memoryCard;
    }

    @Override
    public void connectWithUsbCable() {
        this.memoryCard.insert();
        this.memoryCard.copyData();
    }
}
class User{
    public static void main(String[] args) {
        Usb cardReader = new CardReader(new MemoryCard());
        cardReader.connectWithUsbCable();
    }
}

