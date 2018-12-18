# SpringSecurity
A authority controll way based on spring -security that  can  undertake a any  grading  authority controlling .</br>

1、一个小的安全、权限控制组件，基于spring 5.0+spring-security 5.0+spring boot 2.0.2</br>
2、控件的工作流程：</br>
  2.1 用户，资源，权限，角色等关系实体是基于Oracle数据库的</br>
  2.2 流程说明：</br>
     a  拦截用户的请求，判断当前用户是否登录，没有登录则跳转到用户请求页面进行登录。</br>
     b  实现spring security 用户服务接口UserDetailsService，内部铜鼓访问数据查询用户数据，并且初始化自定义的用户详细信息  UserDetailsImpl，内部封装了Spring security的用户。</br>
     c  在查询用户的信息时，同时查询了当前用户的所有具有的权限信息，可以使用缓存，但是这一版本的没有使用缓存。spring security会把这个信息进行封装得到框架需要的权限集合，请看UserDetailsImpl内部的getAuthorities（）方法。</br>
     d   AccessUrlAuthorizationFilter会拦截用户的请求，检查请求是否带有请求应该具有的权限表示参数auth_code，这个参数表示的意思是访问这个url应该具有的权限编号。</br>
     f   AccessDecisionManagerImpl类会得到AccessUrlAuthorizationFilter返回的需要的权限编号，查询当前用户具有的权限编号，检查是否匹配，只要有一个权限匹配就通过，否则抛出权限不足的异常</br>
     g  如果授权失败，在AuthenticationFailureHandler中处理，成功在AuthenticationSucessHandler类中处理。</br>
     
3、数据结构说明</br>
  ResultJson是自定义封装的统一的数据交换格式，统一按照这样的数据进行前后台交换，内部应该使用map的结构比较好些。</br>  
4、数据库使用的是oracle </br>
 要使用其他数据库也是可以很方便修改的，没有提供数据库建表脚本。但是基本按照权限控制理论来说，就是三个主体表和两个关系表，一共五个表主题表：TESTUSER(用户表)  TESTROLE（角色表） TESTRESOURCE（权限资源表）  TESTUSERROLE（用户角色关系表）  TESTROLERESOURCE(角色资源权限表)</br>
  
5、优点与缺点</br>
   优点：</br>
   1、基于权限码的权限控制，几乎可以覆盖通用常见的权限控制，权限的粒度可以达到操作按钮级别</br></br>
 
  缺点：</br>
   1、缺少数据权限的定义，比如控制查询单个用户与查询所有用户是不同权限，但是往往是同一个API，当然这个问题也不是完全不能解决，只需要分别定义针对单个
  和多个用户查询的权限码。</br>
   2、没有添加权限的页面，当然也可以自己写写。</br>
     
