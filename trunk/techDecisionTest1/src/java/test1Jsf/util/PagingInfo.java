package test1Jsf.util;

public class PagingInfo {
    private int batchSize = 20;
    private int firstItem = 0;
    private Long itemCount = -1L;
    
    public int getBatchSize() {
        return batchSize;
    }
    
    public Long getItemCount() {
        return itemCount;
    }
    
    public void setItemCount(Long itemCount) {
        this.itemCount = itemCount;
    }
    
    public int getFirstItem() {
        if (itemCount == -1) {
            throw new IllegalStateException("itemCount must be set before invoking getFirstItem");
        }
        if (firstItem >= itemCount) {
            if (itemCount == 0) {
                firstItem = 0;
            } else {
                Long zeroBasedItemCount = itemCount - 1;
                double pageDouble = zeroBasedItemCount / batchSize;
                int page = (int) Math.floor(pageDouble);
                firstItem = page * batchSize;
            }
        }
        return firstItem;
    }
    
    public void setFirstItem(int firstItem) {
        this.firstItem = firstItem;
    }
    
    public Long getLastItem() {
        getFirstItem();
        return firstItem + batchSize > itemCount ? itemCount : firstItem + batchSize;
    }
    
    public void nextPage() {
        getFirstItem();
        if (firstItem + batchSize < itemCount) {
            firstItem += batchSize;
        }
    }
    
    public void previousPage() {
        getFirstItem();
        firstItem -= batchSize;
        if (firstItem < 0) {
            firstItem = 0;
        }
    }
}
