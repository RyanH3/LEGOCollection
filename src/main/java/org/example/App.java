package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class App 
{
    private static final SessionFactory factory = new Configuration().configure().buildSessionFactory();
    public static final Logger logger = LogManager.getLogger();

    public static void main( String[] args ) throws InterruptedException {
        /** commented out for use as a site
        int choice = showMenu();
        int setId;
        String setName;
        int piecesAmount;
        float price;

        Scanner scanner = new Scanner(System.in);

        while (choice != -999) {
            switch (choice) {
                case 1:
                    System.out.print("Set name: ");
                    setName = scanner.nextLine();

                    piecesAmount = validateInt("Amount of pieces: ");

                    price = validateFloat("Price of set: ");

                    addItem(setName, piecesAmount, price);

                    System.out.println("New set added.\n");
                    choice = showMenu();
                    break;
                case 2:
                    viewItems();
                    choice = showMenu();
                    break;
                case 3:
                    viewItems();

                    setId = validateInt("Set ID: ");

                    System.out.print("Set name: ");
                    setName = scanner.nextLine();

                    piecesAmount = validateInt("Amount of pieces: ");

                    price = validateFloat("Price of set: ");

                    updateItem(setId, setName, piecesAmount, price);

                    System.out.println("Set updated.\n");
                    choice = showMenu();
                    break;
                case 4:
                    viewItems();

                    setId = validateInt("Set ID: ");

                    deleteItem(setId);

                    System.out.println("Set deleted.\n");

                    choice = showMenu();
                    break;
                case 5:
                    choice = -999;
                    System.out.println("Exiting...");
                    Thread.sleep(1000);
                    break;
                default:
            }
        }
    **/}

    /**
     * Adds a LEGO set to the collection.
     * @param setName A String with the name of the LEGO set.
     * @param piecesAmount An int with the amount of pieces the set has.
     * @param price A float with the price of the set.
     */
    public static void addItem(String setName, int piecesAmount, float price) {
        Session session = factory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            LEGOSet legoSet = new LEGOSet(setName, piecesAmount, price);
            session.persist(legoSet);
            transaction.commit();
            logger.info("{} added", legoSet.getSetName());
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        } finally {
            session.close();
        }
    }

    /**
     * Deletes a LEGO set from the collection.
     * @param setID An int with the ID of the LEGO set.
     */
    public static void deleteItem(int setID) {
        Session session = factory.openSession();
        Transaction transaction = null;

        try {
            boolean isValid = false;

            if (session.createQuery("FROM LEGOSet").list().isEmpty()) {}
            else {
                while (!isValid) {
                    try {
                        transaction = session.beginTransaction();

                        LEGOSet legoSet = (LEGOSet)session.get(LEGOSet.class, setID);
                        session.delete(legoSet);
                        transaction.commit();
                        logger.info("{} deleted", legoSet.getSetName());

                        isValid = true;
                    }
                    catch (Exception e) {
                        logger.warn("Invalid input. Please type an ID in the list.\n");
                    }
                }
            }
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        } finally {
            session.close();
        }
    }

    /**
     * Displays a menu for the user to choose an option.
     * @return An integer representing the choice the user made.
     */
    public static int showMenu() throws InterruptedException {

        boolean isValid = false;
        int choice = 0;

        while (!isValid) {
            try {
                String menuMessage = """
                Welcome to the To-Do List

                1. Add an item
                2. View all items
                3. Update an item
                4. Delete an item
                5. Exit
                Select an option:""";
                System.out.print(menuMessage);

                Scanner scanner = new Scanner(System.in);
                choice = scanner.nextInt();

                if (choice < 1 || choice > 5) {
                    throw new Exception();
                }

                isValid = true;
            }
            catch (Exception e) {
                System.out.println("Invalid input. Please type an integer between 1 and 4.\n");
                Thread.sleep(3000);
            }
        }
        return choice;
    }

    /**
     * Updates a LEGO set in the collection.
     * @param setId An int with the ID of the LEGO set that will be updated.
     * @param setName A String with the new name of the LEGO set.
     * @param piecesAmount An int with the new amount of pieces the set has.
     * @param price A float with the new price of the set.
     */
    public static void updateItem(int setId, String setName, int piecesAmount, float price) {
        Session session = factory.openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        LEGOSet legoSet = (LEGOSet)session.get(LEGOSet.class, setId);

        legoSet.setSetName(setName);
        legoSet.setPiecesAmount(piecesAmount);
        legoSet.setPrice(price);

        session.merge(legoSet);
        transaction.commit();
    }

    /**
     * Displays all the sets in the LEGO collection.
     * @return A list of LEGOSet Items.
     */
    public static ArrayList<LEGOSet> viewItems() {
        Session session = factory.openSession();
        Transaction transaction = null;
        ArrayList<LEGOSet> sets = new ArrayList<LEGOSet>();

        try {
            if (session.createQuery("FROM LEGOSet").list().isEmpty()) {
                System.out.println("The collection is empty. Please populate it first.\n");
            }
            else {
                transaction = session.beginTransaction();
                List rawItems = session.createQuery("FROM LEGOSet").list();
                for (Iterator iterator = rawItems.iterator(); iterator.hasNext();){
                    LEGOSet legoSet = (LEGOSet) iterator.next();
                    sets.add(legoSet);
                    //System.out.println("LEGO Set " + legoSet.getId() + ": " + legoSet.getSetName() + ". " +
                    //        legoSet.getPiecesAmount() + " pieces, $" + legoSet.getPrice());
                }
                System.out.println();
                logger.info("Sets printed");
                transaction.commit();
            }
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        } finally {
            session.close();
        }

        return sets;
    }

    /**
     * Makes sure the user submits a floating-point number.
     * @param promptMessage The message displayed to the user.
     * @return A valid floating-point number.
     * @throws InterruptedException If Thread.sleep gets interrupted.
     */
    public static float validateFloat(String promptMessage) throws InterruptedException {
        boolean isValid = false;
        float validFloat = 0;

        while (!isValid) {
            try {
                System.out.print(promptMessage);
                Scanner scanner = new Scanner(System.in);
                validFloat = Float.parseFloat(scanner.nextLine());
                isValid = true;
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid input. Please type a valid floating-point number.\n");
                Thread.sleep(3000);
            }
        }

        return validFloat;
    }

    /**
     * Makes sure the user submits an integer.
     * @param promptMessage The message displayed to the user.
     * @return A valid integer.
     * @throws InterruptedException If Thread.sleep gets interrupted.
     */
    public static int validateInt(String promptMessage) throws InterruptedException {
        boolean isValid = false;
        int validInt = 0;

        while (!isValid) {
            try {
                System.out.print(promptMessage);
                Scanner scanner = new Scanner(System.in);
                validInt = Integer.parseInt(scanner.nextLine());
                isValid = true;
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid input. Please type a valid integer.\n");
                Thread.sleep(3000);
            }
        }

        return validInt;
    }
}
