��Java����ӳ�䵽sqlite���ݣ�ʵ�ֳ־û�����hibernate������ͬ��֮�


@DatabaseTable(tableName = "accounts")
public class Account {
    @DatabaseField(id = true)
    private String name;
    
    @DatabaseField(canBeNull = false)
    private String password;
    ...
    Account() {
    	// all persisted classes must define a no-arg constructor with at least package visibility
    }
    ...    
}