package sicasm;

/**
 *
 * @author Ahmed Magdy
 */
public class Hashtable {

    private final int N = (int) 1e4 + 7;
    private final String[] Table;
    private final String[][] Table2D;
    private boolean isLITTAB;
    private boolean empty;

    // Constructor create Empty Table
    public Hashtable() {
        this.Table = new String[N];
        this.Table2D = new String[N][4];
        isLITTAB = false;
        empty = true;
    }

    // Constructor create new Table and Seed with operations
    public Hashtable(int Seeder) {
        this.Table = new String[N];
        this.Table2D = new String[N][4];
        Seeder();
    }

    public Hashtable(boolean isLITTAB) {
        this.Table = new String[N];
        this.Table2D = new String[N][4];
        this.isLITTAB = true;
    }

    // Get Hash Key for the Operation and store the Opcode
    public void setHash(String key, String value) {
        int Hash = Hash1(key);
        empty = false;
        if(isLITTAB){
            Table2D[Hash][0] = value;
            Table2D[Hash][1] = getHexValue(value);
            Table2D[Hash][2] = Integer.toHexString(getLength(value));
        }
        else
            Table[Hash] = value;
    }
        
    public void setAddress(String key, String value){
        int Hash = Hash1(key);
        Table2D[Hash][3] = value;
    }

    // Get the Opcode form the table for the operation
    public String getValue(String key, int i) {
        int Hash = Hash1(key);
        if(isLITTAB){
            return Table2D[Hash][i];
        }
        return Table[Hash];
    }

    // Create a Hash Key for the key
    private int Hash1(String key) {
        int Hash1 = 0;
        int CharValue = 0;
        for (int i = 0; i < key.length(); ++i) {
            if (key.charAt(i) >= 'a' && key.charAt(i) <= 'z') {
                CharValue = key.charAt(i) - 'a' + 1;
            } else if (key.charAt(i) >= 'A' && key.charAt(i) <= 'Z') {
                CharValue = key.charAt(i) - 'A' + 1;
            } else
                CharValue = key.charAt(i);
            Hash1 += CharValue * (Math.pow(10, key.length() - i - 1));
        }
        return Hash2(Hash1);
    }

    // Prevent Table Over flow
    private int Hash2(int Hash1) {
        int Hash2 = Hash1 % N;
        return Hash2;
    }

    // Seed the Table with the Operations' Opcodes
    private void Seeder() {
        // Hexadecimal Opcode
        setHash("add", "18");
        setHash("and", "40");
        setHash("comp", "28");
        setHash("div", "24");
        setHash("j", "3C");
        setHash("jeq", "30");
        setHash("jgt", "34");
        setHash("jlt", "38");
        setHash("jsub", "48");
        setHash("lda", "00");
        setHash("ldch", "50");
        setHash("ldl", "08");
        setHash("ldx", "04");
        setHash("mul", "20");
        setHash("or", "44");
        setHash("rd", "D8");
        setHash("rsub", "4C");
        setHash("sta", "0C");
        setHash("stch", "54");
        setHash("stl", "14");
        setHash("stx", "10");
        setHash("sub", "1C");
        setHash("td", "E0");
        setHash("tix", "2C");
        setHash("wd", "DC");
    }

    public String getHexValue(String key) {
        if (!isLITTAB) {
            return null;
        }
        String Value = key;
        if (Value.startsWith("C'") || Value.startsWith("c'")) {
            String Letters = Value.substring(2, Value.length() - 1);
            String HexValue = "";
            for (char Letter : Letters.toCharArray()) {
                HexValue += Integer.toHexString(Letter);
            }
            return HexValue;
        } else if (Value.startsWith("X'") || Value.startsWith("x'")) {
            return Value.substring(2, Value.length() - 1);
        } else {
            return Value;
        }
    }

    public int getLength(String key) {
        if (!isLITTAB) {
            return 0;
        }
        return getHexValue(key).length() / 2;
    }

    /**
     * @return the empty
     */
    public boolean isEmpty() {
        return empty;
    }
}
