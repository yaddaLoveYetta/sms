package com.kingdee.eas.hrp.sms.service.impl.user;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingdee.eas.hrp.sms.dao.UserMapper;
import com.kingdee.eas.hrp.sms.model.User;
import com.kingdee.eas.hrp.sms.model.UserExample;
import com.kingdee.eas.hrp.sms.service.api.user.ILoginService;
import com.kingdee.eas.hrp.sms.service.api.user.IUserServeice;
import com.kingdee.eas.hrp.sms.util.Environ;

@Service
public class UserService implements IUserServeice {

	@Resource
	private SqlSession sqlSession;

	@Override
	public List<User> getUserList(int pageNum, int pageSize) {

		Page<User> page = PageHelper.startPage(pageNum, pageSize, true);

		UserMapper mapper = sqlSession.getMapper(UserMapper.class);

		UserExample example = new UserExample();
		// example.setOrderByClause("userId");
		example.setOrderByClause("userId desc ,name asc");

		// Criteria criteria = example.createCriteria();
		// criteria.andUserIdEqualTo(2);

		List<User> list = mapper.selectByExample(example);

		System.out.println(page.getTotal());
		System.out.println(list.size());

		PageInfo<User> pageInfo = new PageInfo<>(list);

		// assertEquals(1, page.getPageNum());

		// Assert.assertEquals(1, pageInfo.getPageNum());
		// Assert.assertEquals(2, pageInfo.getPageSize());
		// Assert.assertEquals(3, pageInfo.getTotal());
		System.out.println(pageInfo.getOrderBy());
		System.out.println(JSON.toJSONString(list));

		// //用PageInfo对结果进行包装
		// PageInfo page = new PageInfo(list);
		// //测试PageInfo全部属性
		// //PageInfo包含了非常全面的分页属性
		// assertEquals(1, page.getPageNum());
		// assertEquals(10, page.getPageSize());
		// assertEquals(1, page.getStartRow());
		// assertEquals(10, page.getEndRow());
		// assertEquals(183, page.getTotal());
		// assertEquals(19, page.getPages());
		// assertEquals(1, page.getFirstPage());
		// assertEquals(8, page.getLastPage());
		// assertEquals(true, page.isFirstPage());
		// assertEquals(false, page.isLastPage());
		// assertEquals(false, page.isHasPreviousPage());
		// assertEquals(true, page.isHasNextPage());

		return null;
	}

}
