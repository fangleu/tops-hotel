package com.car.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.car.bean.Navigation;
import com.car.common.dao.BaseDaoImpl;
import com.car.dao.INavigationDao;

@Component(value = "navigationDao")
@Transactional
public class NavigationDao extends BaseDaoImpl<Navigation, Serializable> implements INavigationDao{

}