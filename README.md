# Coroutine-ViewModelScope

If we are following the mvvm architecture, we need to always create viewmodels for activities and for fragments.

Here, for understanding, lets create a new android studio project named ViewModelScope demo and add the required gradle dependencies to work with ViewModel, Livedata and coroutines. Lets 
create a new viewModel class and name it as MainActivityViewModel and extend the ViewModel class. In order to run a coroutine in this view model, we need a coroutine scope. Let’s assume, 
we want to launch the coroutine in a background thread ie Dispathers.IO. Then, will create a function getUserData(). Inside this function, we will launch the coroutine. 

In android, everytime a viewmodel is cleared from the memory, just before the clearing, viewmodel invokes its onCleared() method. onCleared() method is always there by default, but, if we 
want to do something just before the clearing, we can manually override the onCleared method. Some of the coroutines we launch in a viewmodel, has a potential to run even after the view 
 model is cleared from the memory. It might run until our app is terminated. In some scenarios that would be the intention but if that's not what we intended, app will end up leaking 
memory. To avoid that, we need to cancel the coroutine within the onCleared funciton. 

![image](https://github.com/user-attachments/assets/98e44d95-d6e2-45ed-a90f-1715f231a439)

In order to cancel coroutines started in a scope, we need to pass a job instance for the context of the coroutine scope. So let's create a job instance & add it to the context of the 
coroutine scope. This will allows us to control all the coroutines launched in this scope. So, to cancel all the corotuiens launched in a scope, only thing we need to do is canceling the 
corouitnes manually and it might be useful in some situations. But if we have 20 viewmodel classes in our app, doing this manually might be unnecessary wasting of time. So, to avoid those 
boiler plate codes, we can use viewModelScope which is bounded to ViewModel’s lifecycle. It is created to automatically handle cancellation when the ViewModel’s onClear() is called. We can
easily use this scope from an extension function available on the viewmodel-ktx library. So, we need to add viewmodel-ktx dependency to the gradle from the following link.

https://developer.android.com/kotlin/ktx/extensions-list

Now, in this view model, we can simply use extension property of viewModelScope instead of the myScope. While using viewModelScope, we don’t need the scope or job and we don’t need to 
override onCleared as clearing will be done automatically.

A ViewModelScope is defined for each ViewModel in our app. Any coroutine launched in this scope will be automatically canceled if the ViewModel is cleared. This kind of coroutines are 
useful when you have work that needs to be done only if the ViewModel is active. 

Let's get some user data from the repository to an activity through the viewmodel. Instead of displaying them in a recycleview, let’s just log the values. So let’s create a new data class 
called User and it will have an int id and a String name. Let's a new class for the Repository and code a getter method to get a list of user instances. In a similar real world situation,
this can be a call to a rest api and get users from it or get a list of users from a local database. 

For the demonstration lets create a new list of objects and return them. Let’s create a suspending functioncalled getUsers which will return a list of users. To mimic a long running task,
add a delay for 5 seconds. Now, we can create a list of users and we will return the list of users.In the view model class, create a new repository instance.
