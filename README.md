# MVPTemplate
Android MVP pattern with Retrofit2

Android Application using MVP Pattern.

Retrieve posts using free API jsonplaceholder.
Implementation of load more and refresh posts using Retrofit2.

Example based on https://github.com/cpinan/DemoRetrofit2

# Presentation

http://tinyurl.com/android-pe-mvp

# What you will find here

A friendly and easy to understand MVP template. You can pick up things that you wish to implement on your project while learning how to correctly implement MVP Pattern.
Also, I'm using Repository Pattern for data handling for further testing purposes.

**Soon: Implementation of testing with Dagger and Mockito.

# What's MVP

MVP stands for Model-View-Presenter pattern. MVP helps to structure your application in order to separate it in 3 different layers. You will be separating your business logic from
the view, this is why the Presenter, will be in charge of the communication between the Model and the View.

Model: 
The model layer contains the objects we are going to use for our application. We might use a Datasource and the APIEndpoint in order to provide this data.

In this example we have the model Post. We are using the Repository pattern in order to provide the data we are going to use in our different builds. This pattern allows us to have different
datasources per build flavour we have on our build.gradle file. In our case, in our test build we can load mock data with our fakeApiService instead of loading the real data from server. This pattern
allows you to have an independent datasource that can be used from different origins (DB, API, mock). 

- More on build flavours: http://developer.android.com/intl/es/tools/building/configuring-gradle.html

- Quick Explanation of Repository Pattern used:

	1.- We have a PostRepository Interface. This interfacce will contain all the methods we currently need to obtain the data from our datasource. Remember this methods can be used to get the data 
		already loaded in memory as calling the web service API. In this example we have getPosts methods that will return a list of Posts.

	2.- Then we have a PostRepositories class. This class will be in charge of getting our correct datasource. Here we can decide if we are going to use our Memory repository or database.

	3.- Our only datasource is InMemoryPostsManager. This class contains the implementation of PostRepository interface. This datasource will provide the already loaded posts in the view or 
	call the webservice get if it is required.

	4.- Our Injection class (One in production package and another one in mock package). This class is made so we can choose which API layer we should use. In this case we use PostServeAPIImp, we might aswell use a FakeServerAPIImp.

	In summary, We have separated all our datasources from each other, making it easier to change between them. Also they are separated from the API layer we use. Both of them can use different layers without having to change any code.

View: 

The view layer is in charge of displaying the data and receiving the user interactions. If an user makes an interaction it will redirect it to the instance of the Presenter it has. After the presenter handles the logic, it will send the data to show to the view. 
Important: Not all the views needs to implement MVP pattern. Static data doesn't require this implementation.

Presenter:

The Presenter layer is in charge of the communication between the View and the Model. It will handle all your business logic and is in charge of delivering it where it was required. I.e. 

# Why should I use MVP? 

MVP architecture allows your Android application to be easy to test and have reusable code.

# What's different

A lot of examples or guides you find don't go deep in how to implement MVP pattern in a project.

Special Thanks to:

	- github.com/cpinan
 	- github.com/gyosida

Sources: 

http://www.code-labs.io/codelabs/android-testing/index.html
https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93presenter
http://hannesdorfmann.com/mosby/getting-started/
https://github.com/cpinan/DemoRetrofit2