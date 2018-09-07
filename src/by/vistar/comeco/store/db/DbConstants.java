package by.vistar.comeco.store.db;

public class DbConstants {
    public static final String TABLE_NAME_GOOD_IN_DOC = "goodindoc";
    public static final String TABLE_NAME_GOOD_DOC = "gooddoc";
    public static final String TABLE_NAME_GOOD_OUT_DOC = "goodoutdoc";
    public static final String TABLE_NAME_STORE = "store";

    //-------------------------- GOOD_IN_DOC ------------------------------

    public static final Integer MAX_NUMBER_DOC = 25;
    public static final Integer MAX_INFO_TEXT = 100;

    public static final String MYSQL_ADD_GOOD_IN_DOC = "INSERT INTO " + TABLE_NAME_GOOD_IN_DOC +
            " (`number_doc`,`date`,`partner_id`,`info`) VALUE (?,?,?,?)";
    public static final String MYSQL_GET_GOOD_IN_DOC = "SELECT * FROM " + TABLE_NAME_GOOD_IN_DOC + " WHERE id=?;";
    public static final String MYSQL_DELL_GOOD_IN_DOC = "DELETE FROM " + TABLE_NAME_GOOD_IN_DOC + " WHERE id=?;";
    public static final String MYSQL_EDIT_GOOD_IN_DOC = "UPDATE " + TABLE_NAME_GOOD_IN_DOC +
            " SET number_doc=?, date=?, partner_id=?, info=? WHERE id=?;";


    public static final String MYSQL_NEW_TABLE_GOOD_IN_DOC =
            "CREATE TABLE IF NOT EXISTS `" + TABLE_NAME_GOOD_IN_DOC + "`(" +
                    "`id` INT(11) NOT NULL AUTO_INCREMENT," +
                    "`number_doc` VARCHAR(" + MAX_NUMBER_DOC + ") NOT NULL DEFAULT 0," +
                    "`date` DATE NOT NULL,`partner_id` INT(11) NOT NULL," +
                    " `info` VARCHAR(" + MAX_INFO_TEXT + ") NULL DEFAULT NULL,PRIMARY KEY (`id`))" +
                    " ENGINE = InnoDB DEFAULT CHARACTER SET = utf8;";
    //--------------------------- GOOD_DOC ------------------------------------

    public static final String MYSQL_ADD_GOOD_DOC = "INSERT INTO " + TABLE_NAME_GOOD_DOC +
            " (`doc_id`,`good_id`,`quantity`,`store_id`,`type_doc`) VALUE (?,?,?,?,?)";
    public static final String MYSQL_GET_GOOD_DOC = "SELECT * FROM " + TABLE_NAME_GOOD_DOC + " WHERE id=?;";
    public static final String MYSQL_DELL_GOOD_DOC = "DELETE FROM " + TABLE_NAME_GOOD_DOC + " WHERE id=?;";
    public static final String MYSQL_EDIT_GOOD_DOC = "UPDATE " + TABLE_NAME_GOOD_DOC +
            " SET doc_id=?, good_id=?, quantity=?, store_id=?, type_doc=? WHERE id=?;";



    public static final String MYSQL_NEW_TABLE_GOOD_DOC =
            "CREATE TABLE IF NOT EXISTS `" + TABLE_NAME_GOOD_DOC + "`(" +
                    "`id` INT(11) NOT NULL AUTO_INCREMENT," +
                    " `doc_id` INT(11) NOT NULL, `good_id`INT(11) NOT NULL," +
                    "`quantity` FLOAT(11) NOT NULL DEFAULT 0, `store_id` INT(11) NOT NULL," +
                    "`type_doc` INT(11) NOT NULL, PRIMARY KEY(`id`)," +
                    " CONSTRAINT `fk_store`" +
                    " FOREIGN KEY(`store_id`) REFERENCES `" + TABLE_NAME_STORE + "`(`id`) ON DELETE NO ACTION" +
                    " ON UPDATE NO ACTION) ENGINE =InnoDB DEFAULT CHARACTER SET =utf8;";

    //---------------------------------- GOOD_OUT_DOC --------------------------------

    public static final String MYSQL_ADD_GOOD_OUT_DOC = "INSERT INTO " + TABLE_NAME_GOOD_OUT_DOC +
            " (`number_doc`,`date`,`partner_id`,`info`) VALUE (?,?,?,?)";
    public static final String MYSQL_GET_GOOD_OUT_DOC = "SELECT * FROM " + TABLE_NAME_GOOD_OUT_DOC + " WHERE id=?;";
    public static final String MYSQL_DELL_GOOD_OUT_DOC = "DELETE FROM " + TABLE_NAME_GOOD_OUT_DOC + " WHERE id=?;";
    public static final String MYSQL_EDIT_GOOD_OUT_DOC = "UPDATE " + TABLE_NAME_GOOD_OUT_DOC +
            " SET number_doc=?, date=?, partner_id=?, info=? WHERE id=?;";

    public static final String MYSQL_NEW_TABLE_GOOD_OUT_DOC =
            "CREATE TABLE IF NOT EXISTS `" + TABLE_NAME_GOOD_OUT_DOC + "`(" +
                    " `id` INT(11) NOT NULL AUTO_INCREMENT, `number_doc` VARCHAR(" + MAX_NUMBER_DOC + ") NOT NULL," +
                    " `date` DATE NOT NULL, `partner_id` INT(11) NOT NULL," +
                    " `info`  VARCHAR(" + MAX_INFO_TEXT + ") NULL DEFAULT NULL, PRIMARY KEY(`id`))" +
                    " ENGINE =InnoDB DEFAULT CHARACTER SET =utf8;";

    //------------------------------------- STORE --------------------------------------
    public static final Integer MAX_NAME = 25;

    public static final String MYSQL_ADD_STORE = "INSERT INTO " + TABLE_NAME_STORE +
            " (`name`,`info`,`by_default`) VALUE (?,?,?)";
    public static final String MYSQL_GET_STORE = "SELECT * FROM " + TABLE_NAME_STORE + " WHERE id=?;";
    public static final String MYSQL_DELL_STORE = "DELETE FROM " + TABLE_NAME_STORE + " WHERE id=?;";
    public static final String MYSQL_EDIT_STORE = "UPDATE " + TABLE_NAME_STORE +
            " SET name=?, info=?, by_default=? WHERE id=?;";


    public static final String MYSQL_NEW_TABLE_STORE =
            "CREATE TABLE IF NOT EXISTS `" + TABLE_NAME_STORE + "`(" +
                    " `id` INT(11) NOT NULL AUTO_INCREMENT, `name` VARCHAR(" + MAX_NAME + ") NOT NULL," +
                    " `info` VARCHAR("+MAX_INFO_TEXT+") NULL DEFAULT NULL," +
                    " `by_default` TINYINT NOT NULL DEFAULT 1,"+
                    " PRIMARY KEY(`id`))" +
                    " ENGINE =InnoDB DEFAULT CHARACTER SET =utf8;";
}
