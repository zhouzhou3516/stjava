package com.zhou.jdbc.abstractdemo.dynamicproxy;

import com.zhou.jdbc.abstractdemo.helperplus.Person;
import java.util.List;

/**
 * Created by liqingzhou on 17/8/9.
 */
public interface PersonDao {
    Person getPerson();
    List<Person> getPersons();
}
