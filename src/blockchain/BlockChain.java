package blockchain;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;


public class BlockChain {

    private final ArrayList<Block> chain;

    public BlockChain() {
        chain = new ArrayList<>(0);
    }

    public void generateNewBlock() {
        int size = chain.size();
        Block headBlock = size > 0 ? chain.get(size - 1) : null;
        boolean hasHeadBlock = headBlock != null;

        long timestamp = new Date().getTime();
        long id = hasHeadBlock ? headBlock.getId() + 1 : 1;
        String previousHash = hasHeadBlock ? headBlock.getCurrentHash() : "0";
        String currentHash = StringUtil.applySha256(previousHash);

        Block newBlock = new Block(id, timestamp, previousHash, currentHash);
        chain.add(newBlock);
    }

    public boolean validateBlocks() {
        var ref = new Object() {
            boolean isValid = true;
        };
        chain.stream().reduce((Block previousBlock, Block currentBlock) -> {
            if (ref.isValid) {
                ref.isValid = Objects.equals(previousBlock.getCurrentHash(), currentBlock.getPreviousHash());
            }
            return currentBlock;
        });
        return ref.isValid;
    }

    public ArrayList<Block> getChain() {
        return chain;
    }
}

class Block {

    private final long id;
    private final long timestamp;
    private final String currentHash;
    private final String previousHash;

    public Block(long id, long timestamp, String previousHash, String currentHash) {
        this.id = id;
        this.timestamp = timestamp;
        this.currentHash = currentHash;
        this.previousHash = previousHash;
    }

    public String getCurrentHash() {
        return currentHash;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public long getId() {
        return id;
    }

    public long getTimestamp() {
        return timestamp;
    }
}

