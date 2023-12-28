# this is the documentation for the Semester end project 
# a few keywords and comments regarding them :
User : 
there are both a RightPassword and RightPassword_ methods for this class because i wanted to keep the password as private as possible which also explains the absence of the getter for this attribute
also the username is just another attribute i added since u always find it in most apps and such i did consider adding an id for admins but that could make the creaion of accounts and login process way too slow
customer :
the couponCodes attribute for this class should have been of type Coupons but instead its of type ArrayList just in case an admin updates the discount for a coupon code
there are no getters nor setters for any item from the shoppingCarrt to highlight the encapsulation of objects that should be private

customerDatabase/AdminDatabase :  
the addUser method doesnt check for the existence of accounts in the database that is because that is accounted for in the adminLogin/customerLogin methods i thought it to be neater this way
storedItem : 
also the more appropriate name might be storedProduct but i thought item would be more appropriate

shoppingItem : 
it inherits from the storedItem class tho logically speaking it shouldnt i thought about making it the same as storedItem but then i would only have one class inherit from Product wich goes against the whole point from an abstract class .
Storage:
it uses the same logic as Userdatabase/AdminDatabase
feedback:
its a container for all the reviews(stars given and additional comment)
it is used for both feedbakc on the app itself and the products
the static temp attribute is used to find the overall review for the app/product (everytime a review is added the rating is added to the temp var then the overall var divides the temp var by the total number of reviews to find the actual rating)




main functions:
decided to treat the options menus as app windows which is why each menu (i.e. mainwindow, customerLogin) are in different functions
the while true loop is my way to allow the user to keep making choices until he decides hes done by closing the app(terminate the process)

v
