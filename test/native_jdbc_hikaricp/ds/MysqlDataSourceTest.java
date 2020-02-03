package native_jdbc_hikaricp.ds;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

public class MysqlDataSourceTest {
	private static Logger logger = LogManager.getLogger();
		
//	@Before
//	public void setUp() throws Exception {}
//	
//	@After
//	public void tearDown() throws Exception{}
	
	@Test
	public void testGetConnection() {
		try(Connection con = MysqlDataSource.getConnection()){
			logger.debug(con);
			Assert.assertNotNull(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
