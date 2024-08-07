import java.util.Scanner;
import java.util.ArrayList;

public class FastFoodBillingSystem {
    public static void main(String[] args) {

        Scanner myInput = new Scanner(System.in);

        // GLOBAL VARIABLES SECTION

        String userQuitStatus;
        String userVatStatus;
        String userDiscountStatus;

        // PAYMENT VARIABLES

        double vatPercent = 0.13;
        double discountAmount = 0.0;
        double totalAmount = 0.0;
        double discountPercent = 0.0;
        double userChange = 0.0;
        double userDebt = 0.0;
        double userPayment = 0.0;

        // CASH NOTES STORING ARRAY

        double[] cashNotes = {1000.0, 500.0, 100.0, 50.0, 20.0, 10.0, 5.0, 2.0, 1.0, 0.50};

        // DISCOUNT COUPON STORING ARRAY

        String[][] discountCoupons = {
                {"DISCOUNT50", "50"},
                {"CHRISTMAS75", "75"},
                {"XMAS80", "80"},
                {"NYR85", "85"},
                {"HAPPY20", "20"},
                {"E1D3N", "95"},
                {"R17G", "90"},
                {"DISCOUNT10","10"}
        };

        // PASSWORD AND UID STORING ARRAYLIST SECTION

        ArrayList<String[]> userIdPwList = new ArrayList<>();
        userIdPwList.add(new String[]{"7764", "PASSWORD7764"});
        userIdPwList.add(new String[]{"1652", "hardpassword123$"});
        userIdPwList.add(new String[]{"8971", "easypassword123$"});
        userIdPwList.add(new String[]{"1987", "nonecanguessthispassword1276$2+@"});
        userIdPwList.add(new String[]{"1983", "t7%8u2I)*;;Owa"});
        userIdPwList.add(new String[]{"1912", "Password"});

        // PASSWORD AND UID INPUT SECTION

        boolean userAuthenticationStatus = false;

        while (!userAuthenticationStatus) {

            System.out.println("Enter your UserID and Password.");

            System.out.print("UserID: ");
            String userId = myInput.nextLine();

            System.out.print("Password: ");
            String userPassword = myInput.nextLine();

            // USER VERIFICATION SECTION

            userAuthenticationStatus = false;

            for (int i = 0; i < userIdPwList.size(); i++) {
                if (userIdPwList.get(i)[0].equals(userId) && userIdPwList.get(i)[1].equals(userPassword)) {
                    userAuthenticationStatus = true;
                    break;
                }
            }

            if (userAuthenticationStatus) {

                System.out.println("Logged In Successfully.");

                // FOOD BILLING SYSTEM SECTION

                ArrayList<String> userItems = new ArrayList<>();
                ArrayList<Integer> userItemQuantities = new ArrayList<>();
                ArrayList<Double> userItemPrice = new ArrayList<>();

                boolean items = true;

                while (items) {

                    // TOTAL AMOUNT WITHOUT VAT CALCULATING SECTION

                    System.out.print("Enter the Item's Name: ");
                    String itemToAdd = myInput.nextLine();

                    System.out.print("Enter the Price For " + itemToAdd + ": ");
                    double itemPrice = myInput.nextDouble();

                    System.out.print("Enter the Number of " + itemToAdd + ": ");
                    int itemQuantity = myInput.nextInt();

                    System.out.println("Item is: " + itemToAdd + " and item Quantity is: " + itemQuantity + " worth " + itemPrice + " each." );

                    userItems.add(itemToAdd);
                    userItemQuantities.add(itemQuantity);
                    userItemPrice.add(itemPrice);

                    System.out.println("Are there more items? (Y/N)");
                    myInput.nextLine();
                    String userItemsMore = myInput.nextLine();

                    if (userItemsMore.toUpperCase().equals("N")) {
                        items = false;
                    }

                }

                for (int i = 0; i < userItems.size(); i++) {
                    totalAmount += userItemPrice.get(i) * userItemQuantities.get(i);
                }

                // VAT PROMPT SECTION

                System.out.println("Your Total Amount Without VAT or Discount is " + totalAmount);

                System.out.println("Do you want to add VAT? (Y/N)");
                userVatStatus = myInput.nextLine();

                if (userVatStatus.toUpperCase().equals("Y")) {
                    totalAmount += totalAmount * vatPercent;
                    System.out.println("Your Total Amount With VAT but without Discount is " + totalAmount);
                }

                // DISCOUNT PROMPT SECTION

                System.out.println("Do You have a Discount Coupon? (Y/N)");
                userDiscountStatus = myInput.nextLine();

                if (userDiscountStatus.toUpperCase().equals("Y")) {

                    System.out.print("Enter your Coupon Code: ");
                    String userCouponCode = myInput.nextLine();
                    boolean userCouponCodeAuthenticity = false;

                    for(int i = 0; i < discountCoupons.length; i++) {

                        if (discountCoupons[i][0].equals(userCouponCode)) {

                            discountPercent = Double.parseDouble(discountCoupons[i][1]);
                            discountAmount = totalAmount * (discountPercent / 100);
                            totalAmount -= discountAmount;

                            userCouponCodeAuthenticity = true;
                            break;
                        }
                    }

                    if (!userCouponCodeAuthenticity) {
                        System.out.println("Coupon Code not Found. Please Enter a Valid Coupon Code.");
                    }

                    System.out.println("Your total amount is " + totalAmount + " after a discount of " + discountAmount);

                    // USER PAYMENT SECTION

                    System.out.println("Please Pay the Required Amount of " + totalAmount + ".");

                    while (userPayment < totalAmount) {
                        System.out.print("Enter your Paying Amount: ");
                        userPayment = myInput.nextDouble();

                        if (userPayment < totalAmount) {
                            userDebt = totalAmount - userPayment;
                            System.out.println("That is Less Than What is to be Paid, You Still Have " + userDebt + " yet to Pay.");
                        }

                        else if (userPayment > totalAmount) {

                            // USER CHANGE CALCULATION IN HIGHEST NOTES SECTION

                            userChange = userPayment - totalAmount;

                            System.out.println("Your Change in Highest Notes is: ");

                            double totalUserChange = userChange;

                            // HIGHEST NOTES CALCULATING SECTION

                            for (int j = 0; j < cashNotes.length; j++) {
                                double noteNumber = cashNotes[j];

                                if (userChange >= noteNumber) {
                                    double noteCount = userChange / noteNumber;
                                    noteCount = (int) noteCount;
                                    userChange -= noteCount * noteNumber;
                                    System.out.println(noteCount + " Notes of " + noteNumber);
                                }
                            }

                            System.out.println("Your Change is " + totalUserChange);
                            System.out.println("Thank You for Your Payment");
                        }

                        else {
                            System.out.println("Thank You for Your Payment.");
                        }

                    }
                }
            }

            else {
                System.out.println("Invalid Username or Password.");
                System.out.println("Do you wish to exit the program? Type 'QUIT' if yes and 'NO' if you want to continue.");
                userQuitStatus = myInput.nextLine();

                if (userQuitStatus.toUpperCase().equals("QUIT")) {
                    System.out.println("Exiting the program.");
                    return;
                }
            }
        }
    }
}
