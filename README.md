# The College Student's Piggy Bank

> ## University: Every 20-year-old child's financial rude awakening.
> ### It's time to start pretending like you have some sense of financial literacy.
>> ### Your Saviour: *The College Student's Piggy Bank*

*The College Student's Piggy Bank* will allow users to:
- Set and manage saving and spending goals
- Analyze spending habits visually
- Keep track of friend-to-friend debts
- Input monthly expenses and calculate an appropriate amount of monthly spending money

This application is **for** university students, **by** a university student. 

Moving away from your parents and into adulthood is hard, and whether you are paying for everything yourself, or you're
getting help, learning how to spend responsibly is tough. Is that $5 Blue Chip coffee every day worth it in the end? 
How much can I be spending on the weekends with my friends? I've asked myself these questions, and my goal for
*The College Student's Piggy Bank* is to make these decisions easier with visuals, goal setting, and recommendations.

> ## User Stories
> ### For P1:
> - As a user, I want to be able to create a new monthly expense and add it to a list of monthly expenses
> - As a user, I want to be able to see an up-to-date amount in my bank account
> - As a user, I want to remove monthly expenses from this month's expenses as I pay them
> - As a user, I want to divide my monthly income into saving and spending categories
> - As a user, I want to save my paid expenses into categories of types of purchases
> 
> ### For P2:
> - As a user, I want to be prompted to save my account data before closing the application
> - As a user, I want to choose between creating a new account or loading my previously saved account
>
> ### Goals For Later:
> - As a user, I want to see a visual representation of my spending habits
> - As a user, I want to apply interest to loans that I have given to my friends
> - As a user, I want to know when I paid certain expenses

> ## Instructions For Use:
> - **You can generate the first required event related to adding X's to Y's by** clicking on the "AddExpense" tab, 
>   inputting the required information, making sure to check off that the expense is "monthly", and clicking add.
> - **You can generate the second required event related to adding X's to Y's by** clicking on the "SeeMonthly" tab, and 
>   you will see this newly added expense. If you click on the expense, you will be able to see the details of the 
>   expense. While expense is selected, click on the pay button beside the description, and the expense will be paid. 
>   The balance on the main tab should decrease and the expense should be removed from the list.
> - You can view previously paid expenses in the "Paid" tab, and clicking on one will show its details.
> - **You can locate my visual component by** clicking on "Main" tab
> - **You can save the state of my application by** clicking yes on the pop-up window when you close the application.
> - **You can reload the state of my application by** clicking "Load" when the application begins.

> ## Event Logging Example:
> Thu Dec 01 13:23:50 PST 2022
> Event log cleared.
>
> Thu Dec 01 13:23:52 PST 2022
> Account loaded from: ./data/MyPiggyBankAccount.json
>
> Thu Dec 01 13:24:07 PST 2022
> Paid new expense titled: Oranges
>
> Thu Dec 01 13:24:24 PST 2022
> Added new monthly expense titled: Hydro Bill
>
> Thu Dec 01 13:24:30 PST 2022
> Paid monthly expense titled: Water Bill
>
> Thu Dec 01 13:24:35 PST 2022
> Account saved to : ./data/MyPiggyBankAccount.json

> ## Reflecting on the Project:
> Reflecting upon my UML Design Diagram, I would do some major refactoring if I had more time. The messiness of the diagram
> shows high levels of cohesion between classes, which is undesirable. Below is a list of changes I would make:
> - Remove the extends relationship between MonthlyFinances and ThisMonthsFinances. ThisMonthsFinances is a subclass of 
>   MonthlyFinances, which implies that it accesses dependencies from its superclass, and it's superclass can be 
>   accessed by other classes via the subclass. However, functionally, I ended up creating very similar dependencies
>   between these classes and others, and barely used the hierarchy. It would make much more sense to separate the two
>   and pass any needed data (which is not much) to the subclass via constructor.
> - Create a "Tab" abstract class, which would contain the necessary implementation that is duplicated between the four
>   main tabs (MainMenuWindow, AddExpenseTab, PaidTab, and SeeMonthlyTab). These classes share a lot of behaviour and
>   are extremely tightly linked, and I think separating some shared information into a superclass would clean it up.
> - I would use the Singleton Pattern for the MyPiggyBank class. The application is built to only accommodate one account,
>   so there is no need for more instances of MyPiggyBank. By implementing this, I would be able to make the singular 
>   MyPiggyBank globally accessible and would not have it passed into so many constructors.
> - Remove the dependencies from SeeMonthlyTab and PaidTab to Expense, and access the information through ThisMonthsFinances
>   instead.