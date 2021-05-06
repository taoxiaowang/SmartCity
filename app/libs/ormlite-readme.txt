将Java对象映射到sqlite数据，实现持久化，与hibernate有异曲同工之妙。


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