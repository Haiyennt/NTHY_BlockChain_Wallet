import java.util.ArrayList;
import com.google.gson.GsonBuilder;
import java.util.Scanner;
public class NTHY_chain {
   public static ArrayList<Block> blockchain = new ArrayList<Block>();
   public static int difficulty = 5;
   
   //public static void main(String[]args) {
    //blockchain.add(new Block("Hi Im the first block","0"));
   // System.out.println("Trying to mine block 1...");
  //  blockchain.get(0).mineBlock(difficulty);
    //blockchain.add(new Block("Yo Im the second block", blockchain.get(blockchain.size()-1).hash));
   //System.out.println("Trying to mine block 2...");
   // blockchain.get(1).mineBlock(difficulty);
   // blockchain.add(new Block("Hey Im third block", blockchain.get(blockchain.size()-1).hash));
   // System.out.println("Trying to mine block 3...");
   // blockchain.get(2).mineBlock(difficulty);
    //System.out.println("\nBlockchain is Valid:" + isChainValid());
    //String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
  // System.out.println("\nThe block chain:");
    //System.out.println(blockchainJson);
   //}

   public static void main(String[]args) 
   {
        Scanner Blockdata = new Scanner(System.in);
        System.out.println("Hay Nhap So Luong Khoi Cua Chuoi: ");
        int i=Integer.parseInt(Blockdata.nextLine());
        if (i > 0)
        {
          for (int j=0;j<i;j++) {
              if (j == 0) {
                  System.out.println("Khoi tao thong tin khoi dau tien");
                  String data = Blockdata.nextLine();
                  blockchain.add(new Block(data,"0"));
                  System.out.println("Try to mine Block 0...");
                  blockchain.get(j).mineBlock(difficulty);
               } else {
            
                  System.out.println("Nhap du lieu khoi thu: " + j);
                  System.out.println("Nhap Ho Ten: ");
                  String hoten = Blockdata.nextLine();
                  System.out.println("Nhap Ma Hop Dong: ");
                  String mahd = Blockdata.nextLine();

                  String chuoi = (new StringBuilder()).append(hoten).append(mahd).toString();
                  blockchain.add(new Block(chuoi, blockchain.get(blockchain.size()-1).hash));
                  System.out.println("Trying to mine Block: " + j);
                  blockchain.get(j).mineBlock(difficulty);
               }
           }
           System.out.print("\nBlockchain is Valid: " + isChainValid());
           String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
           System.out.println("\nThe blockchain: ");
           System.out.println(blockchainJson);

        }
        else
        {
            System.out.println("S??? kh???i ph???i l???n h??n 0");
        }
    }

   public static Boolean isChainValid() {
    Block currentBlock;
    Block previousBlock;
    String hashTarget = new String(new char[difficulty]).replace('\0','0');
    for(int i = 1; i < blockchain.size(); i++) {
        currentBlock = blockchain.get(i);
        previousBlock = blockchain.get(i-1);

        if(!currentBlock.hash.equals(currentBlock.calculateHash())) {
            System.out.println("Current Hashes not equal"); 
            return false;
        }

        if(!previousBlock.hash.equals(currentBlock.previousHash)) {
            System.out.println("Previous Hashes not equal"); 
            return false;
        }

        if(!currentBlock.hash.substring(0, difficulty).equals(hashTarget)) {
            System.out.println("This block hasn't been mined"); 
            return false;
        }

    }
    return true;

    }

}