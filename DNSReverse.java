import java.util.Arrays;

/**
 * Created by Nikhil Verma on 7/12/2016.
 */
public class DNSReverse {
    private static final int TOTALCHARS = 11;

    private class TrieNode {
        TrieNode[] child = new TrieNode[TOTALCHARS];
        String url;
    }

    public static void main(String[] args) {
        DNSReverse dns = new DNSReverse();
        dns.job();

    }

    private void job() {
        String ipAd[] = {"107.108.11.123", "107.109.123.255",
                "74.125.200.106"};
        String url[] = {"www.samsung.com", "www.samsung.net",
                "www.google.in"};
        TrieNode root = new TrieNode();
        for (int i = 0; i < ipAd.length; i++)
            insert(root, ipAd[i].toCharArray(), url[i]);
        System.out.println(findHost(root, ipAd[1].toCharArray()));
        System.out.println(validateIP("125.212.100.abc"));

    }

    private String findHost(TrieNode root, char[] ip) {
        TrieNode temp = root;
        for (char ch : ip) {
            if (temp.child[getIndex(ch)] != null) {
                temp = temp.child[getIndex(ch)];
                if (temp.url != null) {
                    return temp.url;
                }
            }
        }
        return "not_found";
    }

    private void insert(TrieNode root, char[] ip, String host) {
        TrieNode temp = root;
        for (char ch : ip) {
            if (temp.child[getIndex(ch)] == null)
                temp.child[getIndex(ch)] = new TrieNode();
            temp = temp.child[getIndex(ch)];
        }
        temp.url = host;
    }

    private int getIndex(char ch) {
        return ch == '.' ? 10 : ch - '0';
    }

    private boolean validateIP(String ip) {
        String[] ips = ip.split("\\.");
        if (ips.length != 4) return false;
        for (String quar : ips) {
            try {
                int num = Integer.parseInt(quar);
                if (!(num >= 0 && num <= 255)) return false;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }
}
