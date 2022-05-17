package Hasher;

public class Hasher {

    public int hash(String content, int buckets) {
        int hash = (content.hashCode() + 3) % buckets;

        while (hash < 0) {
            hash += 255;
        }

        return hash;
    }
}
