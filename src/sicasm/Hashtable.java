
package sicasm;

/**
 *
 * @author Ahmed Magdy
 */
public class Hashtable {
    
    private final int N=(int)1e4+7;
    private final String[] Table;
    
    // Constructor create Empty Table
    public Hashtable(){
        this.Table = new String[N];
    }
    
    // Constructor create new Table and Seed with operations
    public Hashtable(int Seeder){
        this.Table = new String[N];
        Seeder();
    }
    
    // Get Hash Key for the Operation and store the Opcode
    public void setHash(String operation, String opcode){
        int Hash = Hash1(operation);
        Table[Hash] = opcode;
    }
    
    // Get the Opcode form the table for the operation
    public String getValue(String operation){
        int Hash = Hash1(operation);
        return Table[Hash];
    }
    
    // Create a Hash Key for the operation
    private int Hash1(String operation){
        int Hash1=0;
        int CharValue=0;
        for(int i=0;i<operation.length();++i){
            if(operation.charAt(i)>='a' && operation.charAt(i)<='z')
                CharValue=operation.charAt(i)-'a'+1;
            if(operation.charAt(i)>='A' && operation.charAt(i)<='Z')
                CharValue=operation.charAt(i)-'A'+1;
            Hash1+=CharValue*(Math.pow(10,operation.length()-i-1));
        }
        return Hash2(Hash1);
    }
    
    // Prevent Table Over flow
    private int Hash2(int Hash1){
        int Hash2 = Hash1%N;
        return Hash2;
    }
    
    // Seed the Table with the Operations' Opcodes
    private void Seeder(){
        // Hexadecimal Opcode
        setHash("add","18");
        setHash("and","40");
        setHash("comp","28");
        setHash("div","24");
        setHash("j","3C");
        setHash("jeq","30");
        setHash("jgt","34");
        setHash("jlt","38");
        setHash("jsub","48");
        setHash("lda","00");
        setHash("ldch","50");
        setHash("ldl","08");
        setHash("ldx","04");
        setHash("mul","20");
        setHash("or","44");
        setHash("rd","D8");
        setHash("rsub","4C");
        setHash("sta","0C");
        setHash("stch","54");
        setHash("stl","14");
        setHash("stx","10");
        setHash("sub","1C");
        setHash("td","E0");
        setHash("tix","2C");
        setHash("wd","DC");
    }
}
