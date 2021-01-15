##手写spring事务模块

注意点：
TransactionUtil不要设置为单例，如果是单例可能会发生线程安全问题。


#事务失效的原因

如果在service层用try catch去捕获异常，异常无法抛出，会导致事务aop的afterThrowing无法调用，导致事务无法回滚；


#最佳实践
在service层的需要事务处理的方法中，所有的方法不要去捕获异常，而是将异常抛出。方便事务aop的afterThrowing捕获并进行回滚。