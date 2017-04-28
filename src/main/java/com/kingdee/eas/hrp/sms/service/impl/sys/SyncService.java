package com.kingdee.eas.hrp.sms.service.impl.sys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingdee.eas.hrp.sms.dao.generate.CategoryMapper;
import com.kingdee.eas.hrp.sms.dao.generate.CertificateMapper;
import com.kingdee.eas.hrp.sms.dao.generate.CurrencyMapper;
import com.kingdee.eas.hrp.sms.dao.generate.IndustryMapper;
import com.kingdee.eas.hrp.sms.dao.generate.ItemMapper;
import com.kingdee.eas.hrp.sms.dao.generate.PayMapper;
import com.kingdee.eas.hrp.sms.dao.generate.SettlementMapper;
import com.kingdee.eas.hrp.sms.dao.generate.SupplierMapper;
import com.kingdee.eas.hrp.sms.dao.generate.TaxCategoryMapper;
import com.kingdee.eas.hrp.sms.exception.BusinessLogicRunTimeException;
import com.kingdee.eas.hrp.sms.model.Category;
import com.kingdee.eas.hrp.sms.model.CategoryExample;
import com.kingdee.eas.hrp.sms.model.Certificate;
import com.kingdee.eas.hrp.sms.model.CertificateExample;
import com.kingdee.eas.hrp.sms.model.Currency;
import com.kingdee.eas.hrp.sms.model.CurrencyExample;
import com.kingdee.eas.hrp.sms.model.CurrencyExample.Criteria;
import com.kingdee.eas.hrp.sms.model.Industry;
import com.kingdee.eas.hrp.sms.model.IndustryExample;
import com.kingdee.eas.hrp.sms.model.Item;
import com.kingdee.eas.hrp.sms.model.ItemExample;
import com.kingdee.eas.hrp.sms.model.Pay;
import com.kingdee.eas.hrp.sms.model.PayExample;
import com.kingdee.eas.hrp.sms.model.Settlement;
import com.kingdee.eas.hrp.sms.model.SettlementExample;
import com.kingdee.eas.hrp.sms.model.Supplier;
import com.kingdee.eas.hrp.sms.model.SupplierExample;
import com.kingdee.eas.hrp.sms.model.TaxCategory;
import com.kingdee.eas.hrp.sms.model.TaxCategoryExample;
import com.kingdee.eas.hrp.sms.service.api.sys.ISyncService;
import com.kingdee.eas.hrp.sms.service.impl.BaseService;

@Service
public class SyncService extends BaseService implements ISyncService {

	@Override
	public List<Map<String, Object>> currency(List<Currency> list) {

		// 同步失败的记录
		List<Map<String, Object>> errList = new ArrayList<Map<String, Object>>();

		for (Currency currency : list) {

			Map<String, Object> errItem = new HashMap<String, Object>(); // 记录错误信息

			try {
				sumbitCurrency(currency);
			} catch (Exception e) {
				errItem.put("desc", e.getMessage());
				errItem.put("item", currency);
				errList.add(errItem);
			}

		}
		return errList;
	}

	/**
	 * 提交一条数据到数据库，并提交事务
	 * 
	 * @Title sumbit
	 * @param currency
	 *            void
	 * @date 2017-04-22 22:30:29 星期六
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	private void sumbitCurrency(Currency currency) {

		String currencyId = currency.getId();

		if (null == currencyId) {
			throw new BusinessLogicRunTimeException("内码为空");
		}

		CurrencyMapper mapper = sqlSession.getMapper(CurrencyMapper.class);
		CurrencyExample example = new CurrencyExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(currencyId);
		List<Currency> selectByExample = mapper.selectByExample(example);

		if (selectByExample.size() > 0) {
			// 已经存在
			mapper.updateByPrimaryKeySelective(currency);
		} else {
			// 插入
			mapper.insert(currency);
		}

	}

	@Override
	public List<Map<String, Object>> category(List<Category> list) {

		// 同步失败的记录
		List<Map<String, Object>> errList = new ArrayList<Map<String, Object>>();

		for (Category category : list) {

			Map<String, Object> errItem = new HashMap<String, Object>(); // 记录错误信息

			try {
				sumbitCategory(category);
			} catch (Exception e) {
				errItem.put("desc", e.getMessage());
				errItem.put("item", category);
				errList.add(errItem);
			}

		}
		return errList;
	}

	/**
	 * 提交一条数据到数据库，并提交事务
	 * 
	 * @Title sumbit
	 * @param currency
	 *            void
	 * @date 2017-04-22 22:30:29 星期六
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	private void sumbitCategory(Category category) {

		String categoryId = category.getId();

		if (null == categoryId) {
			throw new BusinessLogicRunTimeException("内码为空");
		}

		CategoryMapper mapper = sqlSession.getMapper(CategoryMapper.class);
		CategoryExample example = new CategoryExample();
		com.kingdee.eas.hrp.sms.model.CategoryExample.Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(categoryId);
		List<Category> selectByExample = mapper.selectByExample(example);

		if (selectByExample.size() > 0) {
			// 已经存在
			mapper.updateByPrimaryKeySelective(category);
		} else {
			// 插入
			mapper.insert(category);
		}

	}

	@Override
	public List<Map<String, Object>> certificate(List<Certificate> list) {

		// 同步失败的记录
		List<Map<String, Object>> errList = new ArrayList<Map<String, Object>>();

		for (Certificate certificate : list) {

			Map<String, Object> errItem = new HashMap<String, Object>(); // 记录错误信息

			try {
				sumbitCertificate(certificate);
			} catch (Exception e) {
				errItem.put("desc", e.getMessage());
				errItem.put("item", certificate);
				errList.add(errItem);
			}

		}
		return errList;
	}

	/**
	 * 提交一条数据到数据库，并提交事务
	 * 
	 * @Title sumbit
	 * @param certificate
	 *            void
	 * @date 2017-04-22 22:30:29 星期六
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	private void sumbitCertificate(Certificate certificate) {

		String certificateId = certificate.getId();

		if (null == certificateId) {
			throw new BusinessLogicRunTimeException("内码为空");
		}

		CertificateMapper mapper = sqlSession.getMapper(CertificateMapper.class);
		CertificateExample example = new CertificateExample();
		com.kingdee.eas.hrp.sms.model.CertificateExample.Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(certificateId);
		List<Certificate> selectByExample = mapper.selectByExample(example);

		if (selectByExample.size() > 0) {
			// 已经存在
			mapper.updateByPrimaryKeySelective(certificate);
		} else {
			// 插入
			mapper.insert(certificate);
		}

	}

	@Override
	public List<Map<String, Object>> industry(List<Industry> list) {

		// 同步失败的记录
		List<Map<String, Object>> errList = new ArrayList<Map<String, Object>>();

		for (Industry industry : list) {

			Map<String, Object> errItem = new HashMap<String, Object>(); // 记录错误信息

			try {
				sumbitIndustry(industry);
			} catch (Exception e) {
				errItem.put("desc", e.getMessage());
				errItem.put("item", industry);
				errList.add(errItem);
			}

		}
		return errList;
	}

	/**
	 * 提交一条数据到数据库，并提交事务
	 * 
	 * @Title sumbit
	 * @param industry
	 *            void
	 * @date 2017-04-22 22:30:29 星期六
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	private void sumbitIndustry(Industry industry) {

		String industryId = industry.getId();

		if (null == industryId) {
			throw new BusinessLogicRunTimeException("内码为空");
		}

		IndustryMapper mapper = sqlSession.getMapper(IndustryMapper.class);
		IndustryExample example = new IndustryExample();
		com.kingdee.eas.hrp.sms.model.IndustryExample.Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(industryId);
		List<Industry> selectByExample = mapper.selectByExample(example);

		if (selectByExample.size() > 0) {
			// 已经存在
			mapper.updateByPrimaryKeySelective(industry);
		} else {
			// 插入
			mapper.insert(industry);
		}

	}

	@Override
	public List<Map<String, Object>> settlement(List<Settlement> list) {

		// 同步失败的记录
		List<Map<String, Object>> errList = new ArrayList<Map<String, Object>>();

		for (Settlement settlement : list) {

			Map<String, Object> errItem = new HashMap<String, Object>(); // 记录错误信息

			try {
				sumbitSettlement(settlement);
			} catch (Exception e) {
				errItem.put("desc", e.getMessage());
				errItem.put("item", settlement);
				errList.add(errItem);
			}

		}
		return errList;
	}

	/**
	 * 提交一条数据到数据库，并提交事务
	 * 
	 * @Title sumbit
	 * @param settlement
	 *            void
	 * @date 2017-04-22 22:30:29 星期六
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	private void sumbitSettlement(Settlement settlement) {

		String settlementId = settlement.getId();

		if (null == settlementId) {
			throw new BusinessLogicRunTimeException("内码为空");
		}

		SettlementMapper mapper = sqlSession.getMapper(SettlementMapper.class);
		SettlementExample example = new SettlementExample();
		com.kingdee.eas.hrp.sms.model.SettlementExample.Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(settlementId);
		List<Settlement> selectByExample = mapper.selectByExample(example);

		if (selectByExample.size() > 0) {
			// 已经存在
			mapper.updateByPrimaryKeySelective(settlement);
		} else {
			// 插入
			mapper.insert(settlement);
		}

	}

	@Override
	public List<Map<String, Object>> pay(List<Pay> list) {

		// 同步失败的记录
		List<Map<String, Object>> errList = new ArrayList<Map<String, Object>>();

		for (Pay pay : list) {

			Map<String, Object> errItem = new HashMap<String, Object>(); // 记录错误信息

			try {
				sumbitPay(pay);
			} catch (Exception e) {
				errItem.put("desc", e.getMessage());
				errItem.put("item", pay);
				errList.add(errItem);
			}

		}
		return errList;
	}

	/**
	 * 提交一条数据到数据库，并提交事务
	 * 
	 * @Title sumbit
	 * @param pay
	 *            void
	 * @date 2017-04-22 22:30:29 星期六
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	private void sumbitPay(Pay pay) {

		String payId = pay.getId();

		if (null == payId) {
			throw new BusinessLogicRunTimeException("内码为空");
		}

		PayMapper mapper = sqlSession.getMapper(PayMapper.class);
		PayExample example = new PayExample();
		com.kingdee.eas.hrp.sms.model.PayExample.Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(payId);
		List<Pay> selectByExample = mapper.selectByExample(example);

		if (selectByExample.size() > 0) {
			// 已经存在
			mapper.updateByPrimaryKeySelective(pay);
		} else {
			// 插入
			mapper.insert(pay);
		}

	}

	@Override
	public List<Map<String, Object>> item(List<Item> list) {

		// 同步失败的记录
		List<Map<String, Object>> errList = new ArrayList<Map<String, Object>>();

		for (Item item : list) {

			Map<String, Object> errItem = new HashMap<String, Object>(); // 记录错误信息

			try {
				sumbitItem(item);
			} catch (Exception e) {
				errItem.put("desc", e.getMessage());
				errItem.put("item", item);
				errList.add(errItem);
			}

		}
		return errList;
	}

	/**
	 * 提交一条数据到数据库，并提交事务
	 * 
	 * @Title sumbit
	 * @param item
	 *            void
	 * @date 2017-04-22 22:30:29 星期六
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	private void sumbitItem(Item item) {

		String itemId = item.getId();

		if (null == itemId) {
			throw new BusinessLogicRunTimeException("内码为空");
		}

		ItemMapper mapper = sqlSession.getMapper(ItemMapper.class);
		ItemExample example = new ItemExample();
		com.kingdee.eas.hrp.sms.model.ItemExample.Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(itemId);
		List<Item> selectByExample = mapper.selectByExample(example);

		if (selectByExample.size() > 0) {
			// 已经存在
			mapper.updateByPrimaryKeySelective(item);
		} else {
			// 插入
			mapper.insert(item);
		}

	}

	@Override
	public List<Map<String, Object>> taxCategory(List<TaxCategory> list) {

		// 同步失败的记录
		List<Map<String, Object>> errList = new ArrayList<Map<String, Object>>();

		for (TaxCategory taxCategory : list) {

			Map<String, Object> errTaxCategory = new HashMap<String, Object>(); // 记录错误信息

			try {
				sumbitTaxCategory(taxCategory);
			} catch (Exception e) {
				errTaxCategory.put("desc", e.getMessage());
				errTaxCategory.put("taxCategory", taxCategory);
				errList.add(errTaxCategory);
			}

		}
		return errList;
	}

	/**
	 * 提交一条数据到数据库，并提交事务
	 * 
	 * @Title sumbit
	 * @param taxCategory
	 *            void
	 * @date 2017-04-22 22:30:29 星期六
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	private void sumbitTaxCategory(TaxCategory taxCategory) {

		String taxCategoryId = taxCategory.getId();

		if (null == taxCategoryId) {
			throw new BusinessLogicRunTimeException("内码为空");
		}

		TaxCategoryMapper mapper = sqlSession.getMapper(TaxCategoryMapper.class);
		TaxCategoryExample example = new TaxCategoryExample();
		com.kingdee.eas.hrp.sms.model.TaxCategoryExample.Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(taxCategoryId);
		List<TaxCategory> selectByExample = mapper.selectByExample(example);

		if (selectByExample.size() > 0) {
			// 已经存在
			mapper.updateByPrimaryKeySelective(taxCategory);
		} else {
			// 插入
			mapper.insert(taxCategory);
		}

	}

	@Override
	public List<Map<String, Object>> supplier(List<Supplier> list) {

		// 同步失败的记录
		List<Map<String, Object>> errList = new ArrayList<Map<String, Object>>();

		for (Supplier supplier : list) {

			Map<String, Object> errSupplier = new HashMap<String, Object>(); // 记录错误信息

			try {
				sumbitSupplier(supplier);
			} catch (Exception e) {
				errSupplier.put("desc", e.getMessage());
				errSupplier.put("supplier", supplier);
				errList.add(errSupplier);
			}

		}
		return errList;
	}

	/**
	 * 提交一条数据到数据库，并提交事务
	 * 
	 * @Title sumbit
	 * @param supplier
	 *            void
	 * @date 2017-04-22 22:30:29 星期六
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	private void sumbitSupplier(Supplier supplier) {

		String supplierId = supplier.getId();

		if (null == supplierId) {
			throw new BusinessLogicRunTimeException("内码为空");
		}

		SupplierMapper mapper = sqlSession.getMapper(SupplierMapper.class);
		SupplierExample example = new SupplierExample();
		com.kingdee.eas.hrp.sms.model.SupplierExample.Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(supplierId);
		List<Supplier> selectByExample = mapper.selectByExample(example);

		if (selectByExample.size() > 0) {
			// 已经存在
			mapper.updateByPrimaryKeySelective(supplier);
		} else {
			// 插入
			mapper.insert(supplier);
		}

	}

	@Override
	public List<Supplier> getSupplierList(int pageNum, int pageSize) {
		Page<Supplier> page = PageHelper.startPage(pageNum, pageSize, true);

		SupplierMapper mapper = sqlSession.getMapper(SupplierMapper.class);

		SupplierExample example = new SupplierExample();
		example.setOrderByClause("supplierId desc ,supplierName asc");

		List<Supplier> list = mapper.selectByExample(example);

		System.out.println(page.getTotal());
		System.out.println(list.size());

		PageInfo<Supplier> pageInfo = new PageInfo<>(list);

		System.out.println(pageInfo.getOrderBy());
		System.out.println(JSON.toJSONString(list));

		return null;
	}

	@Override
	public List<Category> getCategoryList(int pageNum, int pageSize) {
		Page<Category> page = PageHelper.startPage(pageNum, pageSize, true);

		CategoryMapper mapper = sqlSession.getMapper(CategoryMapper.class);

		CategoryExample example = new CategoryExample();
		example.setOrderByClause("categoryId desc ,categoryName asc");

		List<Category> list = mapper.selectByExample(example);

		System.out.println(page.getTotal());
		System.out.println(list.size());

		PageInfo<Category> pageInfo = new PageInfo<>(list);

		System.out.println(pageInfo.getOrderBy());
		System.out.println(JSON.toJSONString(list));

		return null;
	}

	@Override
	public List<Certificate> getCertificateList(int pageNum, int pageSize) {
		Page<Certificate> page = PageHelper.startPage(pageNum, pageSize, true);

		CertificateMapper mapper = sqlSession.getMapper(CertificateMapper.class);

		CertificateExample example = new CertificateExample();
		example.setOrderByClause("certificateId desc ,certificateName asc");

		List<Certificate> list = mapper.selectByExample(example);

		System.out.println(page.getTotal());
		System.out.println(list.size());

		PageInfo<Certificate> pageInfo = new PageInfo<>(list);

		System.out.println(pageInfo.getOrderBy());
		System.out.println(JSON.toJSONString(list));

		return null;
	}

	@Override
	public List<Industry> getIndustryList(int pageNum, int pageSize) {
		Page<Industry> page = PageHelper.startPage(pageNum, pageSize, true);

		IndustryMapper mapper = sqlSession.getMapper(IndustryMapper.class);

		IndustryExample example = new IndustryExample();
		example.setOrderByClause("industryId desc ,industryName asc");

		List<Industry> list = mapper.selectByExample(example);

		System.out.println(page.getTotal());
		System.out.println(list.size());

		PageInfo<Industry> pageInfo = new PageInfo<>(list);

		System.out.println(pageInfo.getOrderBy());
		System.out.println(JSON.toJSONString(list));

		return null;
	}

	@Override
	public List<Currency> getCurrencyList(int pageNum, int pageSize) {
		Page<Currency> page = PageHelper.startPage(pageNum, pageSize, true);

		CurrencyMapper mapper = sqlSession.getMapper(CurrencyMapper.class);

		CurrencyExample example = new CurrencyExample();
		example.setOrderByClause("currencyId desc ,currencyName asc");

		List<Currency> list = mapper.selectByExample(example);

		System.out.println(page.getTotal());
		System.out.println(list.size());

		PageInfo<Currency> pageInfo = new PageInfo<>(list);

		System.out.println(pageInfo.getOrderBy());
		System.out.println(JSON.toJSONString(list));

		return null;
	}

	@Override
	public List<Settlement> getSettlementList(int pageNum, int pageSize) {
		Page<Settlement> page = PageHelper.startPage(pageNum, pageSize, true);

		SettlementMapper mapper = sqlSession.getMapper(SettlementMapper.class);

		SettlementExample example = new SettlementExample();
		example.setOrderByClause("settlementId desc ,settlementName asc");

		List<Settlement> list = mapper.selectByExample(example);

		System.out.println(page.getTotal());
		System.out.println(list.size());

		PageInfo<Settlement> pageInfo = new PageInfo<>(list);

		System.out.println(pageInfo.getOrderBy());
		System.out.println(JSON.toJSONString(list));

		return null;
	}

	@Override
	public List<Pay> getPayList(int pageNum, int pageSize) {
		Page<Pay> page = PageHelper.startPage(pageNum, pageSize, true);

		PayMapper mapper = sqlSession.getMapper(PayMapper.class);

		PayExample example = new PayExample();
		example.setOrderByClause("payId desc ,payName asc");

		List<Pay> list = mapper.selectByExample(example);

		System.out.println(page.getTotal());
		System.out.println(list.size());

		PageInfo<Pay> pageInfo = new PageInfo<>(list);

		System.out.println(pageInfo.getOrderBy());
		System.out.println(JSON.toJSONString(list));

		return null;
	}

	@Override
	public List<Item> getItemList(int pageNum, int pageSize) {
		Page<Item> page = PageHelper.startPage(pageNum, pageSize, true);

		ItemMapper mapper = sqlSession.getMapper(ItemMapper.class);

		ItemExample example = new ItemExample();
		example.setOrderByClause("itemId desc ,itemName asc");

		List<Item> list = mapper.selectByExample(example);

		System.out.println(page.getTotal());
		System.out.println(list.size());

		PageInfo<Item> pageInfo = new PageInfo<>(list);

		System.out.println(pageInfo.getOrderBy());
		System.out.println(JSON.toJSONString(list));

		return null;
	}

	@Override
	public List<TaxCategory> getTaxCategoryList(int pageNum, int pageSize) {
		Page<TaxCategory> page = PageHelper.startPage(pageNum, pageSize, true);

		TaxCategoryMapper mapper = sqlSession.getMapper(TaxCategoryMapper.class);

		TaxCategoryExample example = new TaxCategoryExample();
		example.setOrderByClause("taxCategoryId desc ,taxCategoryName asc");

		List<TaxCategory> list = mapper.selectByExample(example);

		System.out.println(page.getTotal());
		System.out.println(list.size());

		PageInfo<TaxCategory> pageInfo = new PageInfo<>(list);

		System.out.println(pageInfo.getOrderBy());
		System.out.println(JSON.toJSONString(list));

		return null;
	}
}
