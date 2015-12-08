package logicLayer;

import dataAccess.DatabaseException;
import dataAccess.LegalCustomer;
import dataAccess.RealCustomer;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static dataAccess.Customer.createCustomer;
import static dataAccess.LegalCustomerDatabaseHandler.*;
import static dataAccess.RealCustomerDatabaseHandler.*;


public class LayerConnector {

    public static void saveNewLegalCustomer(LegalCustomer legalCustomer) throws DatabaseException, SQLException {

        insertNewLegalCustomerToDatabase(legalCustomer);

    }

    public static void saveNewRealCustomer(RealCustomer realCustomer) {
        insertNewRealCustomerToDatabase(realCustomer);

    }

    public static void deleteLegalCustomer(int id) throws SQLException {
        deleteLegalCustomerFromDatabase(id);
    }

    public static boolean checkValidityOfLegalCustomer(String economicCode) throws SQLException {
        return checkInsertToLegalCustomerDatabase(economicCode);
    }

    public static boolean checkValidityOfRealCustomer(String nationalCode) throws SQLException {
        return checkInsertToRealCustomerDatabase(nationalCode);
    }

    public static boolean changeLegalCustomer(String id, String date, String name, String code) throws ParseException {
        boolean hasChanged = false;
        if (!code.isEmpty()) {
            if (editLegalCustomerEconomicCode(code, id)) {
                hasChanged = true;
                if (!date.isEmpty()) {
                    editCompanyDate(new SimpleDateFormat("yyyy-MM-dd").parse(date), id);
                }
                if (!name.isEmpty()) {
                    editLegalCustomerName(name, id);
                    hasChanged = true;
                }
            }

        } else {
            if (!date.isEmpty()) {
                editCompanyDate(new SimpleDateFormat("yyyy-MM-dd").parse(date), id);
                hasChanged = true;
            }
            if (!name.isEmpty()) {
                editLegalCustomerName(name, id);
                hasChanged = true;

            }
        }
        return hasChanged;
    }

    public static int createLegalCustomer(String code) throws SQLException {
        return createCustomer(code, "Legal");
    }

    public static int createRealCustomer(String code) throws SQLException {
        return createCustomer(code, "Real");
    }

    public static ArrayList searchRealCustomer(ArrayList conditions) throws ParseException, SQLException, ClassNotFoundException {
        return searchRealCustomerInDatabase(conditions);
    }

    public static void deleteRealCustomer(int id) throws SQLException {
        deleteRealCustomerFromDatabase(id);
    }

    public static boolean changeRealCustomer(String id, String name, String family, String father, String nationalCode, String birthDate) throws ParseException {
        boolean hasChanged = false;
        if (!nationalCode.isEmpty()) {
            if (editRealCustomerNationalCode(nationalCode, id)) {
                hasChanged = true;
                if (!birthDate.isEmpty()) {
                    editBirthdate(new SimpleDateFormat("yyyy-MM-dd").parse(birthDate), id);
                }
                if (!name.isEmpty()) {
                    editRealCustomerName(name, id);
                }
                if (!family.isEmpty()) {
                    editRealCustomerFamily(family, id);
                }
                if (!father.isEmpty()) {
                    editRealCustomerFather(father, id);
                }
            }
        } else {
            if (!birthDate.isEmpty()) {
                editCompanyDate(new SimpleDateFormat("yyyy-MM-dd").parse(birthDate), id);
                hasChanged = true;
            }
            if (!name.isEmpty()) {
                editRealCustomerName(name, id);
                hasChanged = true;
            }
            if (!family.isEmpty()) {
                editRealCustomerFamily(family, id);
                hasChanged = true;
            }
            if (!father.isEmpty()) {
                editRealCustomerFather(father, id);
                hasChanged = true;
            }
        }
        return hasChanged;
    }
}
