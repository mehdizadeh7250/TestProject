

# ![]Test Android Technical Specifications
**Last Edited At:** 01/14/2022
**Editors:** S.Saeed Hashemi  

# Table of Contents
* [Introduction](#sec-introduction)
* [Requirements](#sec-requirements)
*  [Project Hierarchy](#sec-projectHierarchy)
* [Dependency Injection](#sec-di)
	* [Database](#sec-databse)
	* [Network](#sec-network)
* [User Interface](#sec-ui)
* [Util](#sec-util)

___
<a id="sec-introduction"></a>
## Introduction 
Test Application is a **Simple** and **Useful** Android app for listing and sorting restaurant.

___
<a id="sec-requirements"></a>
## Requirements 

|Id|Title|Description|
|--|-----|-----------|
|1 | Structural Architecture| MVVM && CLEAN ARCHITECTURE
|2 | Database | Room
|3 | Http Client | Retrofit
|4 | Technologies | AndroidX,Coroutine,LiveData,DataBinding,Hilt,Flow,
|5 | Theme and Components | Material
|6 | Development Language |  Kotlin
|7 | JSON Parser | Moshi

___
<a id="sec-projectHierarchy"></a>
## Project Hierarchy 

* app
    * debug
        * res
            * values
    * main
        * java
            * example.app.test
                * data
                    * repository
                    * source
                       * local
                          * dao 
                       * remote
                          * services 
                * di
                * domain
                    * model 
                      * entityModel 
                      * local 
                      * remote 
                           * response 
                    * repository
                    * usecase
                      * baseUseCase
                * presentation
                    * activity
                    * base
                    * fragment
                    * viewModel
                * utils
                    * customView
                    * flowConverter
                    * helper
        * res
            * anim
            * drawable
            * layout
            * mipmap
            * values
              * themes
            * xml
            
    * release
        * res
            * values

___


<a id="sec-di"></a>
## Dependency Injection
**Hilt** is used to handle di in this project, There are **Database,Network** modules that provide many action  that it injects into **BaseViewModel**

<a id="sec-database"></a>
#### Database
**Entity**  declare in **model** package
**DAOs**  declare in **dao** package, that contains methods to access data of entities
**DatabaseManager:**  provides access to DAOs

<a id="sec-network"></a>
#### Network
**Header Interceptor:**  Add header like **token,client-id,client-secret,...** to each request before call
**NetworkManager:** provides access to each API


___
<a id="sec-ui"></a>
## User Interface:
There are **BaseActivity,BaseFragment,BaseViewModel** classes in **base** package that all activities and fragments and viewModels should extends from those

**Activities** and **Fragments** declare in **ui** package in specific package . for each activity and fragment you it has viewModel

**MyBinding Adapter** provides adapter for common list view. for each list declare **ViewHolder** class in **viewHolder** package,**ItemViewModel** class in **vm** package and a resource in **res/layout** package

___
<a id="sec-util"></a>
## Util:
There are many **Helper** class and **CustomView** class in **util** package, it also contains some useful classes.


