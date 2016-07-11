package com.car.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.car.bean.TestRecord;
import com.car.bean.WinningRecord;
import com.car.common.dao.BaseDaoImpl;
import com.car.dao.ITestRecordDao;
import com.car.dao.IWinningRecordDao;

@Component(value = "winningRecordDao")
@Transactional
public class WinningRecordDao{

}