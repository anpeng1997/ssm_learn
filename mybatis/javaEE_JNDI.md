# JNDI

> JNDI（Java Naming and Directory Interface ），类似于在一个中心注册一个东西，以后要用的时候，只需要根据名字去注册中心查找，注册中心返回你要的东西。web程序，我们可以将一些东西（比如数据库相关的）交给服务器软件去配置和管理（有全局配置和单个web程序的配置），在程序代码中只要通过名称查找就能得到我们注册的东西，而且如果注册的东西有变，比如更换了数据库，我们只需要修改注册信息，名称不改，因此代码也不需要修改