# this is the documentation for the Semester end project
# a few keywords and comments regarding them :
User :
there are both a RightPassword and RightPassword_ methods for this class because I wanted to keep the password as private as possible which also explains the absence of the getter for this attribute
also the username is just another attribute i added since u always find it in most apps and such I did consider adding an id for admins but that could make the creaion of accounts and login process way too slow

customer :
the couponCodes attribute for this class should have been of type Coupons, but instead it's of type ArrayList just in case an admin updates the discount for a coupon code
there are no getters nor setters for any item from the shoppingCart to highlight the encapsulation of objects that should be private

customerDatabase/AdminDatabase :  
the addUser method doesn't check for the existence of accounts in the database that is because that is accounted for in the adminLogin/customerLogin methods i thought it to be neater this way

Product : 
the keywords attribute is my way of implementing a dynamic search function. while realistically it would be better to have categories instead, for this project specifically it would make adding products a lot easier,
plus it gives more freedom.
it is also possible to have two items with the same id or name as long as the other attribute is different 

storedItem :
i should preface this by saying that this is used for both inventory and shopping cart so shoppingItem is completely useless
the more appropriate name might be storedProduct but i thought item makes more sense since it would also be the same attribute used in a shopping cart

shoppingItem :
the only reason it exists is that having an abstract class that only one class inherits from defies the whole reason behind the existance of an abstract class
if the project ever gets updated there might be a use case for it(mostly in tracking orders and stuff like that)

Storage:
the shoppingCart attribute(from customer) and Inventory from Main are this type since they have so much in common
there are many methods called showItems/showall/showMinimal just to give a bit of customisation to the project. it is possible to remove a lot of them but then it would be dull
in the filtered method (which is used to filter the results of a search ) there might be a better way of treating the case where there are no keywords searched but this one is the simplest to implement

review:
consists of a description and stars given out of 5

feedback:
it's a container for all the reviews(stars given and additional comment)
it is used for both feedback on the app itself and the products
the temp attribute is used to find the overall review for the app/product (everytime a review is added the rating is added to the temp var then the overall var divides the temp var by the total number of reviews to find the actual rating)
it might be redundant, but it might prove useful at some point if the project is updated

Coupon : 
the setCode is redundant since changing a Coupon's code doesn't make sense in reality. It exists to keep consistency within a OOP context

Order :
the orders attribute should be of type Storage but having each order be separate makes more sense realistically
whereas for coupons having all the history of coupons seams better

# the Main class 
it might have made more sense to have all the window methods in a seperate class called windows but that would leave the Main class empty
about 90% of the code here is made up of Input/Output and switch statements

mainWindow :
the window you see as soon as you run the code and referred to as the main window in the options

"Sc.nextLine();" is found throughout the code after "x = Sc.nextInt();" it consumes the extra newline character

checkout:
the credit card number/security number are not put into variables since it is useless unless there is creditCardDatabase

showItems :
z is the variable containing all the keywords it is a StringBuilder to allow users to add keywords more than once
it is also possible to not have any filters and you would see all the available products in the inventory
idk