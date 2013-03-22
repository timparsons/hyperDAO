/**
 * 
 */
package com.parsons.dao.util.constant;

/**
 * Collection of constants used when doing anything with SQL
 * 
 * @author Tim
 *
 */
public class SQLConstants {
	
	/* KEYWORDS */
	public static final String SQL_WHERE = " Where ";
	public static final String SQL_COMMA = ", ";
	public static final String SQL_EQUAL_PARAMETER = " = ? ";
	public static final String SQL_SELECT = "Select ";
	public static final String SQL_FROM = " From ";
	public static final String SQL_INSERT = "Insert into ";
	public static final String SQL_UPDATE = "Update ";
	public static final String SQL_DELETE = "Delete from ";
	public static final String SQL_AND = " And ";
	public static final String SQL_OR = " Or ";
	public static final String SQL_SET = " Set ";
	public static final String SQL_VALUES = " Values ";
	public static final String SQL_INNER_JOIN = " Join ";
	public static final String SQL_LEFT_OUTER_JOIN = " Left Join ";
	/* END KEYWORDS */
	
	/* TABLE NAMES */
//	public static final String TABLE_NAME_CUSTOMER = " Customer ";
//	public static final String TABLE_NAME_MEASUREMENT_SYSTEM = " Measurement_System ";
//	public static final String TABLE_NAME_MEASUREMENT = " Measurement ";
//	public static final String TABLE_NAME_MEASUREMENT_CONVERSION = " Measurement_Conversion ";
//	public static final String TABLE_NAME_TAG = " Tag ";
//	public static final String TABLE_NAME_FRIEND = " Friend ";
//	public static final String TABLE_NAME_VISIBILITY = " Visibility ";
//	public static final String TABLE_NAME_RECIPE = " Recipe ";
//	public static final String TABLE_NAME_INGREDIENT = " Ingredient ";
//	public static final String TABLE_NAME_RECIPE_PICTURE = " Recipe_Picture ";
//	public static final String TABLE_NAME_INGREDIENT_PICTURE = " Ingredient_Picture ";
//	public static final String TABLE_NAME_INVENTORY_ITEM = " Inventory_Item ";
//	public static final String TABLE_NAME_INGREDIENT_RECIPE_MAPPING = " Ingredient_Recipe_Mapping ";
//	public static final String TABLE_NAME_STEP = " Step ";
//	public static final String TABLE_NAME_TAG_RECIPE_MAPPING = " Tag_Recipe_Mapping ";
//	public static final String TABLE_NAME_SHARED_RECIPE = " Shared_Recipe ";
//	public static final String TABLE_NAME_LIST = " List ";
//	public static final String TABLE_NAME_LIST_RECIPE_MAPPING = " List_Recipe_Mapping ";
//	public static final String TABLE_NAME_LIST_ITEM = " List_Item ";
//	public static final String TABLE_NAME_SHARED_LIST = " Shared_List ";
//	/* END TABLE NAMES */
//	
//	/* COLUMN NAMES */
//	public static final String COLUMN_NAME_CUSTOMER_ID = "CUSTOMER_ID";
//	public static final String COLUMN_NAME_FIRST_NAME = "FIRST_NAME"; 
//	public static final String COLUMN_NAME_LAST_NAME = "LAST_NAME"; 
//	public static final String COLUMN_NAME_EMAIL = "EMAIL"; 
//	public static final String COLUMN_NAME_ENABLED = "ENABLED"; 
//	public static final String COLUMN_NAME_CODE = "CODE"; 
//	public static final String COLUMN_NAME_MEASUREMENT_SYSTEM_ID = "MEASUREMENT_SYSTEM_ID"; 
//	public static final String COLUMN_NAME_PICTURE = "PICTURE"; 
//	public static final String COLUMN_NAME_CREATE_DATE = "CREATE_DATE"; 
//	public static final String COLUMN_NAME_MODIFIED_DATE = "MODIFIED_DATE"; 
//	public static final String COLUMN_NAME_NAME = "NAME";
//	public static final String COLUMN_NAME_MEASUREMENT_ID = "MEASUREMENT_ID";
//	public static final String COLUMN_NAME_ABBREVIATION = "ABBREVIATION";
//	public static final String COLUMN_NAME_INGREDIENT_ID = "INGREDIENT_ID";
//	public static final String COLUMN_NAME_TAG_ID = "TAG_ID";
//	public static final String COLUMN_NAME_CREATED_BY = "CREATED_BY";
//	public static final String COLUMN_NAME_FRIEND_ID = "FRIEND_ID";
//	public static final String COLUMN_NAME_FRIEND_CUSTOMER_ID = "FRIEND_CUSTOMER_ID";
//	public static final String COLUMN_NAME_RECIPE_ID = "RECIPE_ID";
//	public static final String COLUMN_NAME_REFERENCE = "REFERENCE";
//	public static final String COLUMN_NAME_COOK_TIME = "COOK_TIME";
//	public static final String COLUMN_NAME_PREP_TIME = "PREP_TIME";
//	public static final String COLUMN_NAME_COOK_TEMP = "COOK_TEMP";
//	public static final String COLUMN_NAME_TEMP_MEASUREMENT = "TEMP_MEASUREMENT";
//	public static final String COLUMN_NAME_SERVING_SIZE = "SERVING_SIZE";
//	public static final String COLUMN_NAME_VISIBILITY_ID = "VISIBILITY_ID";
//	public static final String COLUMN_NAME_DESCRIPTION = "DESCRIPTION";
//	public static final String COLUMN_NAME_LEVEL = "LEVEL";
//	public static final String COLUMN_NAME_RECIPE_PICTURE_ID = "RECIPE_PICTURE_ID";
//	public static final String COLUMN_NAME_DEFAULT_PIC = "DEFAULT_PIC";
//	private static final String COLUMN_NAME_INGREDIENT_PICTURE_ID = "INGREDIENT_PICTURE_ID";
//	private static final String COLUMN_NAME_INVENTORY_ITEM_ID = "INVENTORY_ITEM_ID";
//	private static final String COLUMN_NAME_QUANTITY = "QUANTITY";
//	/* END COLUMN NAMES */
//	
//	/* TABLE ALIASES */
//	public static final String MEASUREMENT_SYSTEM_TABLE_ALIAS = "ms.";
//	public static final String CUSTOMER_TABLE_ALIAS = "c.";
//	public static final String MEASUREMENT_TABLE_ALIAS = "m.";
//	public static final String TAG_TABLE_ALIAS = "t.";
//	public static final String FRIEND_TABLE_ALIAS = "f.";
//	public static final String FRIEND_CUSTOMER_TABLE_ALIAS = "fc.";
//	public static final String FRIEND_CUSTOMER_MS_TABLE_ALIAS = "fcms.";
//	public static final String RECIPE_TABLE_ALIAS = "r.";
//	public static final String VISIBILITY_TABLE_ALIAS = "v.";
//	public static final String INGREDIENT_TABLE_ALIAS = "i.";
//	public static final String RECIPE_PICTURE_TABLE_ALIAS = "rp.";
//	public static final String INGREDIENT_PICTURE_TABLE_ALIAS = "ip.";
//	public static final String INVENTORY_ITEM_TABLE_ALIAS = "ii.";
//	public static final String INGREDIENT_RECIPE_MAPPING_TABLE_ALIAS = "irm.";
//	public static final String STEP_TABLE_ALIAS = "s.";
//	public static final String TAG_RECIPE_MAPPING_TABLE_ALIAS = "trm.";
//	public static final String SHARED_RECIPE_TABLE_ALIAS = "sr.";
//	public static final String LIST_TABLE_ALIAS = "l.";
//	public static final String LIST_RECIPE_MAPPING_TABLE_ALIAS = "lrm.";
//	public static final String LIST_ITEM_TABLE_ALIAS = "li.";
//	public static final String SHARED_LIST_TABLE_ALIAS = "sl.";
//	public static final String RECIPE_CUSTOMER_TABLE_ALIAS = "rc.";
//	/* END TABLE ALIASES */
//	
//	/* COLUMN ALIASES */
//	public static final String MEASUREMENT_SYSTEM_COLUMN_ALIAS = "MS_";
//	public static final String CUSTOMER_COLUMN_ALIAS = "C_";
//	public static final String MEASUREMENT_COLUMN_ALIAS = "M_";
//	public static final String TAG_COLUMN_ALIAS = "T_";
//	public static final String FRIEND_COLUMN_ALIAS = "F_";
//	public static final String FRIEND_CUSTOMER_COLUMN_ALIAS = "FC_";
//	public static final String FRIEND_CUSTOMER_MS_COLUMN_ALIAS = "FCMS_";
//	public static final String RECIPE_COLUMN_ALIAS = "R_";
//	public static final String VISIBILITY_COLUMN_ALIAS = "V_";
//	public static final String INGREDIENT_COLUMN_ALIAS = "I_";
//	public static final String RECIPE_PICTURE_COLUMN_ALIAS = "RP_";
//	public static final String INGREDIENT_PICTURE_COLUMN_ALIAS = "IP_";
//	public static final String INVENTORY_ITEM_COLUMN_ALIAS = " ii. ";
//	public static final String INGREDIENT_RECIPE_MAPPING_COLUMN_ALIAS = "IRM_";
//	public static final String STEP_COLUMN_ALIAS = "S_";
//	public static final String TAG_RECIPE_MAPPING_COLUMN_ALIAS = "TRM_";
//	public static final String SHARED_RECIPE_COLUMN_ALIAS = "SR_";
//	public static final String LIST_COLUMN_ALIAS = "L_";
//	public static final String LIST_RECIPE_MAPPING_COLUMN_ALIAS = "LRM_";
//	public static final String LIST_ITEM_COLUMN_ALIAS = "LI_";
//	public static final String SHARED_LIST_COLUMN_ALIAS = "SL_";
//	public static final String RECIPE_CUSTOMER_COLUMN_ALIAS = "RC_";
	/* END COLUMN ALIASES */
	
	/* CUSTOMER QUERIES */
//	public static final String CUSTOMER_CREATE_SQL = SQL_INSERT + TABLE_NAME_CUSTOMER + "( " +
//			COLUMN_NAME_CUSTOMER_ID+SQL_COMMA +
//			COLUMN_NAME_FIRST_NAME+SQL_COMMA +
//			COLUMN_NAME_LAST_NAME+SQL_COMMA +
//			COLUMN_NAME_EMAIL+SQL_COMMA +
//			COLUMN_NAME_ENABLED+SQL_COMMA +
//			COLUMN_NAME_CODE+SQL_COMMA +
//			COLUMN_NAME_MEASUREMENT_SYSTEM_ID+SQL_COMMA +
//			COLUMN_NAME_PICTURE+SQL_COMMA +
//			COLUMN_NAME_CREATE_DATE+SQL_COMMA +
//			COLUMN_NAME_MODIFIED_DATE+") "+ 
//			SQL_VALUES + "(?, " + //CustomerId
//			"?, " + //First_Name
//			"?, " + //Last_Name
//			"?, " + //Email
//			"?, " + //Enabled
//			"?, " + //Code
//			"?, " + //Measurement_system_id
//			"?, " + //Picture
//			"?, " + //Create_Date
//			"?)";   //Modified_Date
//	
//	public static final String CUSTOMER_UPDATE_SQL = SQL_UPDATE + TABLE_NAME_CUSTOMER + SQL_SET +
//			COLUMN_NAME_FIRST_NAME+ SQL_EQUAL_PARAMETER + SQL_COMMA +
//			COLUMN_NAME_LAST_NAME+SQL_EQUAL_PARAMETER + SQL_COMMA +
//			COLUMN_NAME_EMAIL+SQL_EQUAL_PARAMETER + SQL_COMMA +
//			COLUMN_NAME_ENABLED+SQL_EQUAL_PARAMETER + SQL_COMMA +
//			COLUMN_NAME_CODE+SQL_EQUAL_PARAMETER + SQL_COMMA +
//			COLUMN_NAME_MEASUREMENT_SYSTEM_ID+SQL_EQUAL_PARAMETER + SQL_COMMA +
//			COLUMN_NAME_PICTURE+SQL_EQUAL_PARAMETER + SQL_COMMA +
//			COLUMN_NAME_MODIFIED_DATE+ SQL_EQUAL_PARAMETER +
//			SQL_WHERE+COLUMN_NAME_CUSTOMER_ID+SQL_EQUAL_PARAMETER;
//	
//	public static final String CUSTOMER_READ_SQL_SELECT = SQL_SELECT +
//			createCustomerSelect(CUSTOMER_TABLE_ALIAS, null) + SQL_COMMA +
//			createMeasurementSystemSelect(MEASUREMENT_SYSTEM_TABLE_ALIAS, MEASUREMENT_SYSTEM_COLUMN_ALIAS) +
//			SQL_FROM + TABLE_NAME_CUSTOMER + " c " +
//			SQL_INNER_JOIN + TABLE_NAME_MEASUREMENT_SYSTEM + " ms on "+MEASUREMENT_SYSTEM_TABLE_ALIAS+COLUMN_NAME_MEASUREMENT_SYSTEM_ID+" = "+CUSTOMER_TABLE_ALIAS+COLUMN_NAME_MEASUREMENT_SYSTEM_ID+" ";
//	
//	public static final String CUSTOMER_DELETE_SQL = SQL_DELETE + TABLE_NAME_CUSTOMER;
//	/* END CUSTOMER QUERIES */
//	
//	/* MEASUREMENT SYSTEM QUERIES */
//	public static final String MEASUREMENT_SYSTEM_READ = SQL_SELECT + 
//			createMeasurementSystemSelect("", null) + 
//			SQL_FROM + TABLE_NAME_MEASUREMENT_SYSTEM;
//	/* END MEASUREMENT SYSTEM QUERIES */
//	
//	/* MEASUREMENT QUERIES */
//	public static final String MEASUREMENT_MS_NAME = "MS_"+COLUMN_NAME_NAME;
//	public static final String MEASUREMENT_MS_CREATE_DATE = "MS_"+COLUMN_NAME_CREATE_DATE;
//	public static final String MEASUREMENT_READ = SQL_SELECT +
//			createMeasurementSelect(MEASUREMENT_TABLE_ALIAS, null) + SQL_COMMA +
//			createMeasurementSystemSelect(MEASUREMENT_SYSTEM_TABLE_ALIAS, MEASUREMENT_SYSTEM_COLUMN_ALIAS) +
//			SQL_FROM + TABLE_NAME_MEASUREMENT + " m " +
//			SQL_INNER_JOIN + TABLE_NAME_MEASUREMENT_SYSTEM + " ms on "+MEASUREMENT_SYSTEM_TABLE_ALIAS+COLUMN_NAME_MEASUREMENT_SYSTEM_ID + " = "+MEASUREMENT_TABLE_ALIAS+COLUMN_NAME_MEASUREMENT_SYSTEM_ID;
//	/* END MEASUREMENT QUERIES */
//	
//	/* INGREDIENT QUERIES */
//	public static final String INGREDIENT_SAVE = SQL_UPDATE + TABLE_NAME_INGREDIENT +SQL_SET +
//			COLUMN_NAME_INGREDIENT_ID+ SQL_EQUAL_PARAMETER + SQL_COMMA +
//			COLUMN_NAME_NAME+ SQL_EQUAL_PARAMETER + SQL_COMMA +
//			COLUMN_NAME_CREATE_DATE+ SQL_EQUAL_PARAMETER + SQL_COMMA +
//			COLUMN_NAME_MODIFIED_DATE+ SQL_EQUAL_PARAMETER;
//	
//	public static final String INGREDIENT_CREATE = SQL_INSERT + TABLE_NAME_INGREDIENT + " ( " +
//			COLUMN_NAME_INGREDIENT_ID + SQL_COMMA +
//			COLUMN_NAME_NAME + SQL_COMMA +
//			COLUMN_NAME_CREATE_DATE + SQL_COMMA +
//			COLUMN_NAME_MODIFIED_DATE + ") " +
//			SQL_VALUES + "(?, " + //Ingredient_id
//			"?, " + //Name
//			"?, " + //Create_Date
//			"?)"; //Modified_Date
//	
//	public static final String INGREDIENT_READ = SQL_SELECT +
//			createIngredientSelect("", null) + SQL_FROM + TABLE_NAME_INGREDIENT;
//	
//	public static final String INGREDIENT_DELETE = SQL_DELETE + TABLE_NAME_INGREDIENT;
//	/* END INGREDIENT QUERIES */
//	
//	/* TAG QUERIES */
//	public static final String TAG_CUSTOMER_CREATE_DATE = "C_"+COLUMN_NAME_CREATE_DATE;
//	public static final String TAG_SAVE = SQL_UPDATE + TABLE_NAME_TAG +SQL_SET +
//			COLUMN_NAME_TAG_ID+ SQL_EQUAL_PARAMETER + SQL_COMMA +
//			COLUMN_NAME_NAME+ SQL_EQUAL_PARAMETER + SQL_COMMA +
//			COLUMN_NAME_CREATE_DATE+ SQL_EQUAL_PARAMETER + SQL_COMMA +
//			COLUMN_NAME_CREATED_BY+ SQL_EQUAL_PARAMETER;
//	
//	public static final String TAG_CREATE = SQL_INSERT + TABLE_NAME_TAG + " ( " +
//			COLUMN_NAME_TAG_ID + SQL_COMMA +
//			COLUMN_NAME_NAME + SQL_COMMA +
//			COLUMN_NAME_CREATE_DATE + SQL_COMMA +
//			COLUMN_NAME_CREATED_BY + ") " +
//			SQL_VALUES + "(?, " + //Tag_id
//			"?, " + //Name
//			"?, " + //Create_Date
//			"?)"; //Created_By
//	
//	public static final String TAG_READ = SQL_SELECT +
//			TAG_TABLE_ALIAS + COLUMN_NAME_TAG_ID + SQL_COMMA +
//			TAG_TABLE_ALIAS + COLUMN_NAME_NAME + SQL_COMMA +
//			TAG_TABLE_ALIAS + COLUMN_NAME_CREATE_DATE + SQL_COMMA +
//			TAG_TABLE_ALIAS + COLUMN_NAME_CREATED_BY +
//			//CUSTOMER
//			createCustomerSelect(CUSTOMER_TABLE_ALIAS, "C_") + SQL_COMMA +
//			//CUSTOMER MEASUREMENT SYSTEM
//			createMeasurementSystemSelect(MEASUREMENT_SYSTEM_TABLE_ALIAS, MEASUREMENT_SYSTEM_COLUMN_ALIAS) +
//			SQL_FROM + TABLE_NAME_TAG + " t " + 
//			SQL_INNER_JOIN + TABLE_NAME_CUSTOMER + " c on "+CUSTOMER_TABLE_ALIAS+COLUMN_NAME_CUSTOMER_ID+" = "+TAG_TABLE_ALIAS+COLUMN_NAME_CREATED_BY +
//			SQL_INNER_JOIN + TABLE_NAME_MEASUREMENT_SYSTEM + " ms on "+MEASUREMENT_SYSTEM_TABLE_ALIAS+COLUMN_NAME_MEASUREMENT_SYSTEM_ID+" = "+CUSTOMER_TABLE_ALIAS+COLUMN_NAME_MEASUREMENT_SYSTEM_ID+" ";;
//	
//	public static final String TAG_DELETE = SQL_DELETE + " Tag ";
//	/* END TAG QUERIES */
//	
//	/* FRIEND QUERIES */
//	public static final String FRIEND_SAVE = SQL_UPDATE + TABLE_NAME_FRIEND + SQL_SET + 
//			COLUMN_NAME_FRIEND_ID + SQL_EQUAL_PARAMETER + SQL_COMMA + 
//			COLUMN_NAME_CUSTOMER_ID + SQL_EQUAL_PARAMETER + SQL_COMMA + 
//			COLUMN_NAME_FRIEND_CUSTOMER_ID + SQL_EQUAL_PARAMETER + SQL_COMMA + 
//			COLUMN_NAME_CREATE_DATE + SQL_EQUAL_PARAMETER + SQL_COMMA + 
//			COLUMN_NAME_ENABLED + SQL_EQUAL_PARAMETER + SQL_COMMA ;
//	
//	public static final String FRIEND_CREATE = SQL_INSERT + TABLE_NAME_FRIEND + " ( " +
//			COLUMN_NAME_FRIEND_ID + SQL_COMMA + 
//			COLUMN_NAME_CUSTOMER_ID + SQL_COMMA + 
//			COLUMN_NAME_FRIEND_CUSTOMER_ID + SQL_COMMA + 
//			COLUMN_NAME_CREATE_DATE + SQL_COMMA + 
//			COLUMN_NAME_ENABLED + ")" +
//			SQL_VALUES + "(?, "+ //Friend_Id
//			"?, " + //Customer_Id
//			"?, " + //Friend_Customer_Id
//			"?, " + //Create_Date
//			"?)"; //Enabled
//	
//	public static final String FRIEND_READ = SQL_SELECT + 
//			FRIEND_TABLE_ALIAS + COLUMN_NAME_FRIEND_ID + SQL_COMMA + 
//			FRIEND_TABLE_ALIAS + COLUMN_NAME_CUSTOMER_ID + SQL_COMMA + 
//			FRIEND_TABLE_ALIAS + COLUMN_NAME_FRIEND_CUSTOMER_ID + SQL_COMMA + 
//			FRIEND_TABLE_ALIAS + COLUMN_NAME_CREATE_DATE + SQL_COMMA + 
//			FRIEND_TABLE_ALIAS + COLUMN_NAME_ENABLED + 
//			//CUSTOMER
//			createCustomerSelect(CUSTOMER_TABLE_ALIAS, CUSTOMER_COLUMN_ALIAS) + SQL_COMMA +
//			//CUSTOMER MEASUREMENT SYSTEM
//			createMeasurementSystemSelect(MEASUREMENT_SYSTEM_TABLE_ALIAS, MEASUREMENT_SYSTEM_COLUMN_ALIAS) + SQL_COMMA +
//			//FRIEND_CUSTOMER
//			createCustomerSelect(FRIEND_CUSTOMER_TABLE_ALIAS, FRIEND_CUSTOMER_COLUMN_ALIAS) + SQL_COMMA +
//			//FRIEND_CUSTOMER MEASUREMENT SYSTEM
//			createMeasurementSystemSelect(FRIEND_CUSTOMER_MS_TABLE_ALIAS, FRIEND_CUSTOMER_MS_COLUMN_ALIAS) + 
//			SQL_FROM + TABLE_NAME_FRIEND + " f " +
//			SQL_INNER_JOIN + TABLE_NAME_CUSTOMER + " c on "+CUSTOMER_TABLE_ALIAS+COLUMN_NAME_CUSTOMER_ID+" = "+FRIEND_TABLE_ALIAS+COLUMN_NAME_CUSTOMER_ID +
//			SQL_INNER_JOIN + TABLE_NAME_MEASUREMENT_SYSTEM + " ms on "+MEASUREMENT_SYSTEM_TABLE_ALIAS+COLUMN_NAME_MEASUREMENT_SYSTEM_ID+" = "+CUSTOMER_TABLE_ALIAS+COLUMN_NAME_MEASUREMENT_SYSTEM_ID+
//			SQL_INNER_JOIN + TABLE_NAME_CUSTOMER + " fc on "+FRIEND_CUSTOMER_TABLE_ALIAS+COLUMN_NAME_CUSTOMER_ID+" = "+FRIEND_TABLE_ALIAS+COLUMN_NAME_FRIEND_CUSTOMER_ID +
//			SQL_INNER_JOIN + TABLE_NAME_MEASUREMENT_SYSTEM + " fcms on "+FRIEND_CUSTOMER_MS_TABLE_ALIAS+COLUMN_NAME_MEASUREMENT_SYSTEM_ID+" = "+FRIEND_CUSTOMER_TABLE_ALIAS+COLUMN_NAME_MEASUREMENT_SYSTEM_ID+" ";
//	
//	public static final String FRIEND_DELETE = SQL_DELETE + TABLE_NAME_FRIEND;
//	/* END FRIEND QUERIES */
//	
//	/* VISIBILITY QUERIES */
//	public static final String VISIBILITY_SAVE = SQL_UPDATE + TABLE_NAME_VISIBILITY + SQL_SET +
//			COLUMN_NAME_VISIBILITY_ID + SQL_EQUAL_PARAMETER + SQL_COMMA +
//			COLUMN_NAME_DESCRIPTION + SQL_EQUAL_PARAMETER + SQL_COMMA +
//			COLUMN_NAME_LEVEL + SQL_EQUAL_PARAMETER;
//	
//	public static final String VISIBILITY_CREATE = SQL_INSERT + TABLE_NAME_VISIBILITY + " (" +
//			COLUMN_NAME_VISIBILITY_ID + SQL_COMMA +
//			COLUMN_NAME_DESCRIPTION + SQL_COMMA +
//			COLUMN_NAME_LEVEL + 
//			SQL_VALUES + "(?" + //Visibility_Id
//			"?, " + //description
//			"?)"; //level
//	
//	public static final String VISIBILITY_READ = SQL_SELECT + 
//			createVisibilitySelect("", null) +
//			SQL_FROM + TABLE_NAME_VISIBILITY;
//	
//	public static final String VISIBILITY_DELETE = SQL_DELETE + TABLE_NAME_VISIBILITY;
//	/* END VISIBILITY QUERIES */
//	
//	/* RECIPE QUERIES */
//	public static final String RECIPE_SAVE = SQL_UPDATE + TABLE_NAME_RECIPE + SQL_SET + 
//			COLUMN_NAME_RECIPE_ID + SQL_EQUAL_PARAMETER + SQL_COMMA + 
//			COLUMN_NAME_CUSTOMER_ID + SQL_EQUAL_PARAMETER + SQL_COMMA + 
//			COLUMN_NAME_NAME + SQL_EQUAL_PARAMETER + SQL_COMMA + 
//			COLUMN_NAME_REFERENCE + SQL_EQUAL_PARAMETER + SQL_COMMA + 
//			COLUMN_NAME_COOK_TIME + SQL_EQUAL_PARAMETER + SQL_COMMA + 
//			COLUMN_NAME_PREP_TIME + SQL_EQUAL_PARAMETER + SQL_COMMA + 
//			COLUMN_NAME_COOK_TEMP + SQL_EQUAL_PARAMETER + SQL_COMMA + 
//			COLUMN_NAME_TEMP_MEASUREMENT + SQL_EQUAL_PARAMETER + SQL_COMMA + 
//			COLUMN_NAME_SERVING_SIZE + SQL_EQUAL_PARAMETER + SQL_COMMA + 
//			COLUMN_NAME_CREATE_DATE + SQL_EQUAL_PARAMETER + SQL_COMMA + 
//			COLUMN_NAME_MODIFIED_DATE + SQL_EQUAL_PARAMETER + SQL_COMMA + 
//			COLUMN_NAME_VISIBILITY_ID + SQL_EQUAL_PARAMETER;
//	
//	public static final String RECIPE_CREATE = SQL_INSERT + TABLE_NAME_RECIPE + " (" +
//			COLUMN_NAME_RECIPE_ID + SQL_COMMA + 
//			COLUMN_NAME_CUSTOMER_ID + SQL_COMMA + 
//			COLUMN_NAME_NAME + SQL_COMMA + 
//			COLUMN_NAME_REFERENCE + SQL_COMMA + 
//			COLUMN_NAME_COOK_TIME + SQL_COMMA + 
//			COLUMN_NAME_PREP_TIME + SQL_COMMA + 
//			COLUMN_NAME_COOK_TEMP + SQL_COMMA + 
//			COLUMN_NAME_TEMP_MEASUREMENT + SQL_COMMA + 
//			COLUMN_NAME_SERVING_SIZE + SQL_COMMA + 
//			COLUMN_NAME_CREATE_DATE + SQL_COMMA + 
//			COLUMN_NAME_MODIFIED_DATE + SQL_COMMA + 
//			COLUMN_NAME_VISIBILITY_ID + 
//			SQL_VALUES + "(?, " + //Recipe_Id
//			"?, " + //Customer_Id
//			"?, " + //Name
//			"?, " + //Reference
//			"?, " + //Cook_Time
//			"?, " + //Prep_Time
//			"?, " + //Cook_Temp
//			"?, " + //Temp_Measurement
//			"?, " + //Serving_Size
//			"?, " + //Create_Date
//			"?, " + //Modified_Date
//			"?)"; //Visibility_Id
//	
//	public static final String RECIPE_READ = SQL_SELECT + 
//			createRecipeSelect(RECIPE_TABLE_ALIAS, null) + SQL_COMMA +
//			createCustomerSelect(CUSTOMER_TABLE_ALIAS, CUSTOMER_COLUMN_ALIAS) + SQL_COMMA +
//			createVisibilitySelect(VISIBILITY_TABLE_ALIAS, VISIBILITY_COLUMN_ALIAS) + 
//			SQL_FROM + TABLE_NAME_RECIPE + " r " +
//			SQL_INNER_JOIN + TABLE_NAME_CUSTOMER + " c on " + CUSTOMER_TABLE_ALIAS+COLUMN_NAME_CUSTOMER_ID + " = " + RECIPE_TABLE_ALIAS+COLUMN_NAME_CUSTOMER_ID +
//			SQL_INNER_JOIN + TABLE_NAME_VISIBILITY + " v on " + VISIBILITY_TABLE_ALIAS+COLUMN_NAME_VISIBILITY_ID + " = " + RECIPE_TABLE_ALIAS+COLUMN_NAME_VISIBILITY_ID;
//	
//	public static final String RECIPE_DELETE = SQL_DELETE + TABLE_NAME_RECIPE;
//	
//	/* END RECIPE QUERIES */
//	
//	/* RECIPE PICTURE QUERIES */
//	public static final String RECIPE_PICTURE_SAVE = SQL_UPDATE + TABLE_NAME_RECIPE_PICTURE + SQL_SET +
//			COLUMN_NAME_RECIPE_PICTURE_ID + SQL_EQUAL_PARAMETER + SQL_COMMA +
//			COLUMN_NAME_RECIPE_ID + SQL_EQUAL_PARAMETER + SQL_COMMA +
//			COLUMN_NAME_DEFAULT_PIC + SQL_EQUAL_PARAMETER + SQL_COMMA +
//			COLUMN_NAME_ENABLED + SQL_EQUAL_PARAMETER + SQL_COMMA +
//			COLUMN_NAME_PICTURE + SQL_EQUAL_PARAMETER + SQL_COMMA +
//			COLUMN_NAME_CREATE_DATE + SQL_EQUAL_PARAMETER + SQL_COMMA +
//			COLUMN_NAME_CREATED_BY + SQL_EQUAL_PARAMETER;
//	
//	public static final String RECIPE_PICTURE_CREATE = SQL_INSERT + TABLE_NAME_RECIPE_PICTURE + " (" +
//			COLUMN_NAME_RECIPE_PICTURE_ID + SQL_COMMA +
//			COLUMN_NAME_RECIPE_ID + SQL_COMMA +
//			COLUMN_NAME_DEFAULT_PIC + SQL_COMMA +
//			COLUMN_NAME_ENABLED + SQL_COMMA +
//			COLUMN_NAME_PICTURE + SQL_COMMA +
//			COLUMN_NAME_CREATE_DATE + SQL_COMMA +
//			COLUMN_NAME_CREATED_BY + 
//			SQL_VALUES + "(?, "+ //Recipe_Picture_Id
//			"?, " + //Recipe_Id
//			"?, " + //Default_Pic
//			"?, " + //Enabled
//			"?, " + //Picture
//			"?, " + //Create_Date
//			"?)"; //Created_By
//	
//	public static final String RECIPE_PICTURE_READ = SQL_SELECT + 
//			COLUMN_NAME_RECIPE_PICTURE_ID + SQL_COMMA +
//			COLUMN_NAME_RECIPE_ID + SQL_COMMA +
//			COLUMN_NAME_DEFAULT_PIC + SQL_COMMA +
//			COLUMN_NAME_ENABLED + SQL_COMMA +
//			COLUMN_NAME_PICTURE + SQL_COMMA +
//			COLUMN_NAME_CREATE_DATE + SQL_COMMA +
//			COLUMN_NAME_CREATED_BY + SQL_COMMA +
//			createRecipeSelect(RECIPE_TABLE_ALIAS, RECIPE_COLUMN_ALIAS) + SQL_COMMA +
//			// get the customer who owns the recipe
//			createCustomerSelect(RECIPE_CUSTOMER_TABLE_ALIAS, RECIPE_CUSTOMER_COLUMN_ALIAS) +
//			createVisibilitySelect(VISIBILITY_TABLE_ALIAS, VISIBILITY_COLUMN_ALIAS) + SQL_COMMA +
//			// get the customer who owns the picture
//			createCustomerSelect(CUSTOMER_TABLE_ALIAS, CUSTOMER_COLUMN_ALIAS) + 
//			SQL_FROM + TABLE_NAME_RECIPE_PICTURE + " rp " +
//			SQL_INNER_JOIN + TABLE_NAME_RECIPE + " r on " + RECIPE_TABLE_ALIAS+COLUMN_NAME_RECIPE_ID + " = " + RECIPE_PICTURE_TABLE_ALIAS+COLUMN_NAME_RECIPE_ID +
//			SQL_INNER_JOIN + TABLE_NAME_CUSTOMER + " c on " + CUSTOMER_TABLE_ALIAS+COLUMN_NAME_CUSTOMER_ID + " = " + RECIPE_PICTURE_TABLE_ALIAS+COLUMN_NAME_CREATED_BY +
//			SQL_INNER_JOIN + TABLE_NAME_VISIBILITY + " v on " + VISIBILITY_TABLE_ALIAS+COLUMN_NAME_VISIBILITY_ID + " = " + RECIPE_TABLE_ALIAS+COLUMN_NAME_VISIBILITY_ID +
//			SQL_INNER_JOIN + TABLE_NAME_CUSTOMER + " rc on " + RECIPE_CUSTOMER_TABLE_ALIAS+COLUMN_NAME_CUSTOMER_ID + " = " + RECIPE_TABLE_ALIAS+COLUMN_NAME_CREATED_BY ;
//	
//	public static final String RECIPE_PICTURE_DELETE = SQL_DELETE + TABLE_NAME_RECIPE_PICTURE;
//	/* END RECIPE PICTURE QUERIES */
//	
//	/* INGREDIENT PICTURE QUERIES */
//	public static final String INGREDIENT_PICTURE_SAVE = SQL_UPDATE + TABLE_NAME_INGREDIENT_PICTURE + SQL_SET +
//			COLUMN_NAME_INGREDIENT_PICTURE_ID + SQL_EQUAL_PARAMETER + SQL_COMMA +
//			COLUMN_NAME_INGREDIENT_ID + SQL_EQUAL_PARAMETER + SQL_COMMA +
//			COLUMN_NAME_DEFAULT_PIC + SQL_EQUAL_PARAMETER + SQL_COMMA +
//			COLUMN_NAME_ENABLED + SQL_EQUAL_PARAMETER + SQL_COMMA +
//			COLUMN_NAME_PICTURE + SQL_EQUAL_PARAMETER + SQL_COMMA +
//			COLUMN_NAME_CREATE_DATE + SQL_EQUAL_PARAMETER + SQL_COMMA +
//			COLUMN_NAME_CREATED_BY + SQL_EQUAL_PARAMETER;
//	
//	public static final String INGREDIENT_PICTURE_CREATE = SQL_INSERT + TABLE_NAME_INGREDIENT_PICTURE + " (" +
//			COLUMN_NAME_INGREDIENT_PICTURE_ID + SQL_COMMA +
//			COLUMN_NAME_INGREDIENT_ID + SQL_COMMA +
//			COLUMN_NAME_DEFAULT_PIC + SQL_COMMA +
//			COLUMN_NAME_ENABLED + SQL_COMMA +
//			COLUMN_NAME_PICTURE + SQL_COMMA +
//			COLUMN_NAME_CREATE_DATE + SQL_COMMA +
//			COLUMN_NAME_CREATED_BY + 
//			SQL_VALUES + "(?, "+ //Ingredient_Picture_Id
//			"?, " + //Ingredient_Id
//			"?, " + //Default_Pic
//			"?, " + //Enabled
//			"?, " + //Picture
//			"?, " + //Create_Date
//			"?)"; //Created_By
//	
//	public static final String INGREDIENT_PICTURE_READ = SQL_SELECT + 
//			COLUMN_NAME_INGREDIENT_PICTURE_ID + SQL_COMMA +
//			COLUMN_NAME_INGREDIENT_ID + SQL_COMMA +
//			COLUMN_NAME_DEFAULT_PIC + SQL_COMMA +
//			COLUMN_NAME_ENABLED + SQL_COMMA +
//			COLUMN_NAME_PICTURE + SQL_COMMA +
//			COLUMN_NAME_CREATE_DATE + SQL_COMMA +
//			COLUMN_NAME_CREATED_BY + SQL_COMMA +
//			// get the customer who owns the picture
//			createCustomerSelect(CUSTOMER_TABLE_ALIAS, CUSTOMER_COLUMN_ALIAS) + 
//			SQL_FROM + TABLE_NAME_INGREDIENT_PICTURE + " ip " +
//			SQL_INNER_JOIN + TABLE_NAME_CUSTOMER + " c on " + CUSTOMER_TABLE_ALIAS+COLUMN_NAME_CUSTOMER_ID + " = " + INGREDIENT_PICTURE_TABLE_ALIAS+COLUMN_NAME_CREATED_BY +
//			SQL_INNER_JOIN + TABLE_NAME_INGREDIENT + " i on " + INGREDIENT_TABLE_ALIAS+COLUMN_NAME_INGREDIENT_ID + " = " + INGREDIENT_PICTURE_TABLE_ALIAS+COLUMN_NAME_INGREDIENT_ID;
//	
//	public static final String INGREDIENT_PICTURE_DELETE = SQL_DELETE + TABLE_NAME_INGREDIENT_PICTURE;
//	/* END INGREDIENT PICTURE QUERIES */
//	
//	/* INVENTORY ITEM QUERIES */
//	public static final String INVENTORY_ITEM_SAVE = SQL_UPDATE + TABLE_NAME_INVENTORY_ITEM + SQL_SET +
//			COLUMN_NAME_INVENTORY_ITEM_ID + SQL_EQUAL_PARAMETER + SQL_COMMA +
//			COLUMN_NAME_CUSTOMER_ID + SQL_EQUAL_PARAMETER + SQL_COMMA +
//			COLUMN_NAME_INGREDIENT_ID + SQL_EQUAL_PARAMETER + SQL_COMMA +
//			COLUMN_NAME_QUANTITY + SQL_EQUAL_PARAMETER + SQL_COMMA +
//			COLUMN_NAME_MEASUREMENT_ID + SQL_EQUAL_PARAMETER + SQL_COMMA +
//			COLUMN_NAME_CREATE_DATE + SQL_EQUAL_PARAMETER + SQL_COMMA +
//			COLUMN_NAME_MODIFIED_DATE + SQL_EQUAL_PARAMETER;
//	
//	public static final String INVENTORY_ITEM_CREATE = SQL_INSERT + TABLE_NAME_INVENTORY_ITEM + "(" +
//			COLUMN_NAME_INVENTORY_ITEM_ID + SQL_COMMA +
//			COLUMN_NAME_CUSTOMER_ID + SQL_COMMA +
//			COLUMN_NAME_INGREDIENT_ID + SQL_COMMA +
//			COLUMN_NAME_QUANTITY + SQL_COMMA +
//			COLUMN_NAME_MEASUREMENT_ID + SQL_COMMA +
//			COLUMN_NAME_CREATE_DATE + SQL_COMMA +
//			COLUMN_NAME_MODIFIED_DATE +
//			SQL_VALUES + "(?, " + //Inventory_Item_Id
//			"?, " + //Customer_Id
//			"?, " + //Ingredient_Id
//			"?, " + //Quantity
//			"?, " + //Measurement_Id
//			"?, " + //Create_Date
//			"?)"; //Modified_Date
//	
//	public static final String INVENTORY_ITEM_READ = SQL_SELECT + 
//			COLUMN_NAME_INVENTORY_ITEM_ID + SQL_COMMA +
//			COLUMN_NAME_CUSTOMER_ID + SQL_COMMA +
//			COLUMN_NAME_INGREDIENT_ID + SQL_COMMA +
//			COLUMN_NAME_QUANTITY + SQL_COMMA +
//			COLUMN_NAME_MEASUREMENT_ID + SQL_COMMA +
//			COLUMN_NAME_CREATE_DATE + SQL_COMMA +
//			COLUMN_NAME_MODIFIED_DATE + SQL_COMMA +
//			createCustomerSelect(CUSTOMER_TABLE_ALIAS, CUSTOMER_COLUMN_ALIAS) + SQL_COMMA +
//			createIngredientSelect(INGREDIENT_TABLE_ALIAS, INGREDIENT_COLUMN_ALIAS) + SQL_COMMA +
//			createMeasurementSelect(MEASUREMENT_TABLE_ALIAS, MEASUREMENT_COLUMN_ALIAS) + 
//			SQL_FROM + TABLE_NAME_INVENTORY_ITEM + " ii " +
//			SQL_INNER_JOIN + TABLE_NAME_CUSTOMER + " c on " + CUSTOMER_TABLE_ALIAS+COLUMN_NAME_CUSTOMER_ID + " = " + INVENTORY_ITEM_TABLE_ALIAS+COLUMN_NAME_CUSTOMER_ID +
//			SQL_INNER_JOIN + TABLE_NAME_INGREDIENT + " i on " + INGREDIENT_TABLE_ALIAS+COLUMN_NAME_INGREDIENT_ID + " = " + INVENTORY_ITEM_TABLE_ALIAS+COLUMN_NAME_INGREDIENT_ID +
//			SQL_INNER_JOIN + TABLE_NAME_MEASUREMENT_SYSTEM + " ms on " + MEASUREMENT_SYSTEM_TABLE_ALIAS+COLUMN_NAME_MEASUREMENT_SYSTEM_ID + " = " + INVENTORY_ITEM_TABLE_ALIAS+COLUMN_NAME_MEASUREMENT_SYSTEM_ID;
//	
//	public static final String INVENTORY_ITEM_DELETE = SQL_DELETE + TABLE_NAME_INVENTORY_ITEM;
//	/* END INVENTORY ITEM QUERIES */
//	
//	public static enum READ_QUERY {
//		CUSTOMER(TABLE_NAME_CUSTOMER, CUSTOMER_READ_SQL_SELECT);
//		
//		private String tableName;
//		private String sql;
//		
//		private READ_QUERY(String tableName, String sql) {
//			this.tableName = tableName;
//			this.sql = sql;
//		}
//
//		public String getTableName() {
//			return tableName;
//		}
//
//		public String getSql() {
//			return sql;
//		}
//		
//		public static final READ_QUERY getQuery(String tableName) {
//			for(READ_QUERY query : READ_QUERY.values()) {
//				if(query.getTableName().equals(tableName)) {
//					return query;
//				}
//			}
//			
//			return null;
//		}
//	}
//
//	/**
//	 * @param customerColumnAlias
//	 * @param friendC
//	 * @return
//	 */
//	private static String createCustomerSelect(String customerTableAlias,String customerColumnAlias) {
//		return 	customerTableAlias + COLUMN_NAME_FIRST_NAME +addColumnAlias(customerColumnAlias, COLUMN_NAME_FIRST_NAME)+ SQL_COMMA +
//				customerTableAlias + COLUMN_NAME_LAST_NAME +addColumnAlias(customerColumnAlias, COLUMN_NAME_LAST_NAME)+ SQL_COMMA +
//				customerTableAlias + COLUMN_NAME_EMAIL +addColumnAlias(customerColumnAlias, COLUMN_NAME_EMAIL)+ SQL_COMMA +
//				customerTableAlias + COLUMN_NAME_ENABLED +addColumnAlias(customerColumnAlias, COLUMN_NAME_ENABLED)+ SQL_COMMA +
//				customerTableAlias + COLUMN_NAME_CODE +addColumnAlias(customerColumnAlias, COLUMN_NAME_CODE)+ SQL_COMMA +
//				customerTableAlias + COLUMN_NAME_MEASUREMENT_SYSTEM_ID +addColumnAlias(customerColumnAlias, COLUMN_NAME_MEASUREMENT_SYSTEM_ID)+ SQL_COMMA +
//				customerTableAlias + COLUMN_NAME_PICTURE +addColumnAlias(customerColumnAlias, COLUMN_NAME_PICTURE)+ SQL_COMMA +
//				customerTableAlias + COLUMN_NAME_CREATE_DATE +addColumnAlias(customerColumnAlias, COLUMN_NAME_CREATE_DATE) + SQL_COMMA +
//				customerTableAlias + COLUMN_NAME_MODIFIED_DATE +addColumnAlias(customerColumnAlias, COLUMN_NAME_MODIFIED_DATE);
//	}
//
//	/**
//	 * @param measurementTableAlias
//	 * @param measurementColumnAlias
//	 * @return
//	 */
//	private static String createMeasurementSelect(String measurementTableAlias, String measurementColumnAlias) {
//		return measurementTableAlias + COLUMN_NAME_MEASUREMENT_ID + SQL_COMMA +
//				measurementTableAlias + COLUMN_NAME_NAME + SQL_COMMA +
//				measurementTableAlias + COLUMN_NAME_MEASUREMENT_SYSTEM_ID + SQL_COMMA +
//				measurementTableAlias + COLUMN_NAME_ABBREVIATION + SQL_COMMA +
//				measurementTableAlias + COLUMN_NAME_CREATE_DATE;
//	}
//
//	/**
//	 * @param ingredientTableAlias
//	 * @param ingredientColumnAlias
//	 * @return
//	 */
//	private static String createIngredientSelect(String ingredientTableAlias, String ingredientColumnAlias) {
//		return 	ingredientTableAlias + COLUMN_NAME_INGREDIENT_ID + addColumnAlias(ingredientColumnAlias, COLUMN_NAME_INGREDIENT_ID) + SQL_COMMA +
//				ingredientTableAlias + COLUMN_NAME_NAME + addColumnAlias(ingredientColumnAlias, COLUMN_NAME_NAME) + SQL_COMMA +
//				ingredientTableAlias + COLUMN_NAME_CREATE_DATE + addColumnAlias(ingredientColumnAlias, COLUMN_NAME_MODIFIED_DATE) + SQL_COMMA +
//				ingredientTableAlias + COLUMN_NAME_MODIFIED_DATE + addColumnAlias(ingredientColumnAlias, COLUMN_NAME_MODIFIED_DATE);
//	}
//
//	/**
//	 * @param visibilityTableAlias
//	 * @param visibilityColumnAlias
//	 * @return
//	 */
//	private static String createVisibilitySelect(String visibilityTableAlias, String visibilityColumnAlias) {
//		return 	visibilityTableAlias + COLUMN_NAME_VISIBILITY_ID + addColumnAlias(visibilityColumnAlias, COLUMN_NAME_VISIBILITY_ID) + SQL_COMMA +
//				visibilityTableAlias + COLUMN_NAME_DESCRIPTION + addColumnAlias(visibilityColumnAlias, COLUMN_NAME_DESCRIPTION) + SQL_COMMA +
//				visibilityTableAlias + COLUMN_NAME_LEVEL + addColumnAlias(visibilityColumnAlias, COLUMN_NAME_LEVEL);
//	}
//
//	/**
//	 * @param recipeTableAlias
//	 * @param object
//	 * @return
//	 */
//	private static String createRecipeSelect(String recipeTableAlias, String recipeColumnAlias) {
//		return  recipeTableAlias + COLUMN_NAME_RECIPE_ID + addColumnAlias(recipeColumnAlias, COLUMN_NAME_RECIPE_ID)+SQL_COMMA + 
//				recipeTableAlias + COLUMN_NAME_CUSTOMER_ID + addColumnAlias(recipeColumnAlias, COLUMN_NAME_CUSTOMER_ID)+SQL_COMMA + 
//				recipeTableAlias + COLUMN_NAME_NAME + addColumnAlias(recipeColumnAlias, COLUMN_NAME_NAME)+SQL_COMMA + 
//				recipeTableAlias + COLUMN_NAME_REFERENCE + addColumnAlias(recipeColumnAlias, COLUMN_NAME_REFERENCE)+SQL_COMMA + 
//				recipeTableAlias + COLUMN_NAME_COOK_TIME + addColumnAlias(recipeColumnAlias, COLUMN_NAME_COOK_TIME)+SQL_COMMA + 
//				recipeTableAlias + COLUMN_NAME_PREP_TIME + addColumnAlias(recipeColumnAlias, COLUMN_NAME_PREP_TIME)+SQL_COMMA + 
//				recipeTableAlias + COLUMN_NAME_COOK_TEMP + addColumnAlias(recipeColumnAlias, COLUMN_NAME_COOK_TEMP)+SQL_COMMA + 
//				recipeTableAlias + COLUMN_NAME_TEMP_MEASUREMENT + addColumnAlias(recipeColumnAlias, COLUMN_NAME_TEMP_MEASUREMENT)+SQL_COMMA + 
//				recipeTableAlias + COLUMN_NAME_SERVING_SIZE + addColumnAlias(recipeColumnAlias, COLUMN_NAME_SERVING_SIZE)+SQL_COMMA + 
//				recipeTableAlias + COLUMN_NAME_CREATE_DATE + addColumnAlias(recipeColumnAlias, COLUMN_NAME_SERVING_SIZE)+SQL_COMMA + 
//				recipeTableAlias + COLUMN_NAME_MODIFIED_DATE + addColumnAlias(recipeColumnAlias, COLUMN_NAME_MODIFIED_DATE)+SQL_COMMA + 
//				recipeTableAlias + COLUMN_NAME_VISIBILITY_ID +addColumnAlias(recipeColumnAlias, COLUMN_NAME_VISIBILITY_ID);
//	}
//
//	/**
//	 * @param measurementSystemTableAlias
//	 * @param measurementSystemColumnAlias
//	 * @return
//	 */
//	private static String createMeasurementSystemSelect(String measurementSystemTableAlias, String measurementSystemColumnAlias) {
//		return  measurementSystemTableAlias + COLUMN_NAME_MEASUREMENT_SYSTEM_ID + addColumnAlias(measurementSystemColumnAlias, COLUMN_NAME_MEASUREMENT_SYSTEM_ID) + SQL_COMMA +
//				measurementSystemTableAlias + COLUMN_NAME_NAME +addColumnAlias(measurementSystemColumnAlias, COLUMN_NAME_NAME) +SQL_COMMA +
//				measurementSystemTableAlias + COLUMN_NAME_CREATE_DATE +addColumnAlias(measurementSystemColumnAlias, COLUMN_NAME_CREATE_DATE);
//	}
//	
//	private static String addColumnAlias(String columnAliasPrefix, String columnName) {
//		String ret = "";
//		if(columnAliasPrefix != null) {
//			ret = " AS " + columnAliasPrefix + columnName;
//		}
//		
//		return ret;
//	}

}
