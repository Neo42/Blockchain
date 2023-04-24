package blockchain;

public class Main {
    public static void main(String[] args) {
        BlockChain blockChain = new BlockChain();

        for (int i = 0; i < 5; i++) {
            blockChain.generateNewBlock();
        }

        blockChain.getChain().forEach(block -> {
            System.out.println("Block:");
            System.out.println("Id: " + block.getId());
            System.out.println("Timestamp: " + block.getTimestamp());
            System.out.println("Hash of the previous block: ");
            System.out.println(block.getPreviousHash());
            System.out.println("Hash of the block: ");
            System.out.println(block.getCurrentHash());
            System.out.println();
        });
    }
}


