package au.edu.cdu.semiexact.util;

/**
 * 
 * some common constant values are stored here
 */
public class ConstantValue {
	public static final int IMPOSSIBLE_VALUE = -1;
	public static final String BLANK = " ";

	public static final int LABEL_TYPE_START = 0;
	public static final int LABEL_TYPE_VERTEX = 1;
	public static final int LABEL_TYPE_EDGE = 2;

	public static final int MATE_EXPOSE = -1;

	// time limit of algorithm running
	public static final long RUNNING_TIME_LIMIT = 15 * 60 * 1000000000L;

	// data set
	public static final String DATASET_DIMACS = "DIMACS";

	// database table/view names
	public static final String DB_TBNAME_DATASET = "dataset";
	public static final String DB_TBNAME_INS = "instance";
	public static final String DB_TBNAME_ALG1 = "alg1running";
	public static final String DB_TBNAME_ALG2 = "alg2running";

	public static final String DB_VNAME_INS = "v_instance";
	public static final String DB_VNAME_INS_OPT = "v_instance_opt";
	// database column names
	public static final String DB_COL_ID = "id";

	public static final String DB_COL_INS_ID = "i_id";
	public static final String DB_COL_INS_CODE = "i_code";
	public static final String DB_COL_INS_NAME = "i_name";
	public static final String DB_COL_INS_PATH_NAME = "path_name";
	public static final String DB_COL_DATASET_PATH_NAME = "d_path";


	public static final String DB_COL_V_COUNT = "v_count";
 	public static final String DB_COL_E_COUNT = "e_count";

 	public static final String DB_COL_BATCH_NUM = "batch_num";
	public static final String DB_COL_RESULT_SIZE = "result_size";
	public static final String DB_COL_RUNNING_TIME = "running_nano_sec";
	
	public static final String DB_COL_BEST_RESULT_SIZE = "best_result_size";
	public static final String DB_COL_BEST_RUNNING_TIME = "best_running_nano_sec";
	
	public static final String DB_COL_ACCEPT_RESULT_SIZE = "accepted_result_size";
	public static final String DB_COL_UNACCEPT_RESULT_SIZE = "unaccepted_result_size";

}
