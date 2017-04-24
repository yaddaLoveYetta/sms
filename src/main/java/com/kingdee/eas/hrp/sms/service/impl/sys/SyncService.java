package com.kingdee.eas.hrp.sms.service.impl.sys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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

	Map<String, JSONObject> eJson = new HashMap<>();

	// 同步供应商资料
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Map<String, JSONObject> supplier(JSONArray list) {
		SupplierMapper mapper = sqlSession.getMapper(SupplierMapper.class);
		SupplierExample example = new SupplierExample();
		for (Object obj : list) {
			JSONObject jo = (JSONObject) obj;
			Supplier supplier = new Supplier();

			try {
				// 参数校验
				if (null == jo.getInteger("categoryId") || null == jo.getInteger("industryId") || null == jo.getInteger("supplierId") || null == jo.getInteger("supplierId")
						|| null == jo.getInteger("taxCategoryId") || null == jo.getInteger("certificateId") || null == jo.getInteger("currencyId") || null == jo.getInteger("settlementId")
						|| null == jo.getInteger("payId") || null == jo.getInteger("itemId") || null == jo.getInteger("number")) {
					throw new BusinessLogicRunTimeException("ID类字段不能为空");
				}
				// 装载userbean
				supplier.setAddress(jo.getString("address"));
				supplier.setBanOrganization(jo.getString("banOrganization"));
				supplier.setBRNO(jo.getString("BRNO"));
				supplier.setCategoryId(jo.getInteger("categoryId"));
				supplier.setCity(jo.getString("city"));
				supplier.setCORP(jo.getString("CORP"));
				supplier.setCountry(jo.getString("country"));
				supplier.setCreateOrganization(jo.getString("createOrganization"));
				supplier.setIndustryId(jo.getInteger("industryId"));
				supplier.setProvince(jo.getString("province"));
				supplier.setStatus(jo.getInteger("status"));
				supplier.setSupplierId(jo.getInteger("supplierId"));
				supplier.setName(jo.getString("supplierName"));
				supplier.setTaxCategoryId(jo.getInteger("taxCategoryId"));
				supplier.setTaxId(jo.getInteger("taxId"));
				supplier.setTaxRate(jo.getInteger("taxRate"));
				supplier.setCertificateId(jo.getInteger("certificateId"));
				supplier.setCurrencyId(jo.getInteger("currencyId"));
				supplier.setSettlementId(jo.getInteger("settlementId"));
				supplier.setPayId(jo.getInteger("payId"));
				supplier.setItemId(jo.getInteger("itemId"));
				supplier.setNumber(jo.getString("number"));

				com.kingdee.eas.hrp.sms.model.SupplierExample.Criteria criteria = example.createCriteria();
				criteria.andSupplierIdEqualTo(jo.getInteger("supplierId"));

				List<Supplier> suppliers = mapper.selectByExample(example);
				// 数据库存在记录即更新数据，反之插入数据
				if (null != suppliers && suppliers.size() == 1) {
					mapper.updateByExample(supplier, example);

				} else {
					int i = mapper.insert(supplier);
					System.out.println(i);
				}
			} catch (BusinessLogicRunTimeException e) {
				// 参数校验失败，把该条数据放进异常数据返回
				eJson.put(e.toString(), jo);
			}
		}
		return eJson;
	}

	// 查询供应商资料（list）
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.kingdee.eas.hrp.sms.service.impl.sys.ISyncService#getSupplierList( int, int)
	 */
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

	// 同步分类
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Map<String, JSONObject> category(JSONArray list) {
		CategoryMapper mapper = sqlSession.getMapper(CategoryMapper.class);
		CategoryExample example = new CategoryExample();
		for (Object obj : list) {
			JSONObject jo = (JSONObject) obj;
			Category category = new Category();

			try {
				// 参数校验
				if (null == jo.getInteger("categoryId") || null == jo.getString("categoryName")) {
					throw new BusinessLogicRunTimeException("所有字段都不能为空!");
				}
				// 装载userbean
				category.setCategoryId(jo.getInteger("categoryId"));
				category.setName(jo.getString("categoryName"));
				category.setNumber(jo.getString("number"));

				com.kingdee.eas.hrp.sms.model.CategoryExample.Criteria criteria = example.createCriteria();
				criteria.andCategoryIdEqualTo(jo.getInteger("categoryId"));

				List<Category> categories = mapper.selectByExample(example);
				// 数据库存在记录即更新数据，反之插入数据
				if (null != categories && categories.size() == 1) {
					mapper.updateByExample(category, example);
				} else {
					mapper.insert(category);
				}

			} catch (BusinessLogicRunTimeException e) {
				// 参数校验失败，把该条数据放进异常数据返回
				eJson.put(e.toString(), jo);
			}
		}
		return eJson;
	}

	// 查询分类（list）
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.kingdee.eas.hrp.sms.service.impl.sys.ISyncService#getCategoryList( int, int)
	 */
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

	// 同步证书
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Map<String, JSONObject> certificate(JSONArray list) {
		CertificateMapper mapper = sqlSession.getMapper(CertificateMapper.class);
		CertificateExample example = new CertificateExample();
		for (Object obj : list) {
			JSONObject jo = (JSONObject) obj;
			Certificate certificate = new Certificate();

			try {
				// 参数校验
				if (null == jo.getInteger("categoryId") || null == jo.getString("categoryName")) {
					throw new BusinessLogicRunTimeException("所有字段都不能为空!");
				}
				// 装载userbean
				certificate.setCertificateId(jo.getInteger("certificateId"));
				certificate.setName(jo.getString("certificateName"));
				certificate.setNumber(jo.getString("number"));

				com.kingdee.eas.hrp.sms.model.CertificateExample.Criteria criteria = example.createCriteria();
				criteria.andCertificateIdEqualTo(jo.getInteger("certificateId"));

				List<Certificate> certificates = mapper.selectByExample(example);
				// 数据库存在记录即更新数据，反之插入数据
				if (null != certificates && certificates.size() == 1) {
					mapper.updateByExample(certificate, example);
				} else {
					mapper.insert(certificate);
				}
			} catch (BusinessLogicRunTimeException e) {
				// 参数校验失败，把该条数据放进异常数据返回
				eJson.put(e.toString(), jo);
			}
		}
		return eJson;
	}

	// 查询证书（list）
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.kingdee.eas.hrp.sms.service.impl.sys.ISyncService#getCertificateList( int, int)
	 */
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

	// 同步行业
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Map<String, JSONObject> industry(JSONArray list) {
		IndustryMapper mapper = sqlSession.getMapper(IndustryMapper.class);
		IndustryExample example = new IndustryExample();
		for (Object obj : list) {
			JSONObject jo = (JSONObject) obj;
			Industry industry = new Industry();

			try {
				// 参数校验
				if (null == jo.getInteger("categoryId") || null == jo.getString("categoryName")) {
					throw new BusinessLogicRunTimeException("所有字段都不能为空!");
				}
				// 装载userbean
				industry.setIndustryId(jo.getInteger("industryId"));
				industry.setName(jo.getString("industryName"));
				industry.setNumber(jo.getString("number"));

				com.kingdee.eas.hrp.sms.model.IndustryExample.Criteria criteria = example.createCriteria();
				criteria.andIndustryIdEqualTo(jo.getInteger("industryId"));

				List<Industry> industries = mapper.selectByExample(example);
				// 数据库存在记录即更新数据，反之插入数据
				if (null != industries && industries.size() == 1) {
					mapper.updateByExample(industry, example);
				} else {
					mapper.insert(industry);
				}
			} catch (BusinessLogicRunTimeException e) {
				// 参数校验失败，把该条数据放进异常数据返回
				eJson.put(e.toString(), jo);
			}
		}
		return eJson;
	}

	// 查询证书（list）
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.kingdee.eas.hrp.sms.service.impl.sys.ISyncService#getIndustryList( int, int)
	 */
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

	// 同步币别
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Map<String, JSONObject> currency(JSONArray list) {
		CurrencyMapper mapper = sqlSession.getMapper(CurrencyMapper.class);
		CurrencyExample example = new CurrencyExample();
		for (Object obj : list) {
			JSONObject jo = (JSONObject) obj;
			Currency currency = new Currency();

			try {
				// 参数校验
				if (null == jo.getInteger("categoryId") || null == jo.getString("categoryName")) {
					throw new BusinessLogicRunTimeException("所有字段都不能为空!");
				}
				// 装载userbean
				currency.setCurrencyId(jo.getInteger("currencyId"));
				currency.setName(jo.getString("currencyName"));
				currency.setNumber(jo.getString("number"));

				com.kingdee.eas.hrp.sms.model.CurrencyExample.Criteria criteria = example.createCriteria();
				criteria.andCurrencyIdEqualTo(jo.getInteger("currencyId"));

				List<Currency> currencies = mapper.selectByExample(example);
				// 数据库存在记录即更新数据，反之插入数据
				if (null != currencies && currencies.size() == 1) {
					mapper.updateByExample(currency, example);
				} else {
					mapper.insert(currency);
				}
			} catch (BusinessLogicRunTimeException e) {
				// 参数校验失败，把该条数据放进异常数据返回
				eJson.put(e.toString(), jo);
			}
		}
		return eJson;
	}

	// 查询币别（list）
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.kingdee.eas.hrp.sms.service.impl.sys.ISyncService#getCurrencyList( int, int)
	 */
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

	// 同步结算方式
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Map<String, JSONObject> settlement(JSONArray list) {
		SettlementMapper mapper = sqlSession.getMapper(SettlementMapper.class);
		SettlementExample example = new SettlementExample();
		for (Object obj : list) {
			JSONObject jo = (JSONObject) obj;
			Settlement settlement = new Settlement();

			try {
				// 参数校验
				if (null == jo.getInteger("categoryId") || null == jo.getString("categoryName")) {
					throw new BusinessLogicRunTimeException("所有字段都不能为空!");
				}
				// 装载userbean
				settlement.setSettlementId(jo.getInteger("settlementId"));
				settlement.setSettlementName(jo.getString("settlementName"));
				settlement.setNumber(jo.getString("number"));

				com.kingdee.eas.hrp.sms.model.SettlementExample.Criteria criteria = example.createCriteria();
				criteria.andSettlementIdEqualTo(jo.getInteger("settlementId"));

				List<Settlement> settlements = mapper.selectByExample(example);
				// 数据库存在记录即更新数据，反之插入数据
				if (null != settlements && settlements.size() == 1) {
					mapper.updateByExample(settlement, example);
				} else {
					mapper.insert(settlement);
				}
			} catch (BusinessLogicRunTimeException e) {
				// 参数校验失败，把该条数据放进异常数据返回
				eJson.put(e.toString(), jo);
			}
		}
		return eJson;
	}

	// 查询结算方式（list）
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.kingdee.eas.hrp.sms.service.impl.sys.ISyncService#getSettlementList( int, int)
	 */
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

	// 同步付款方式
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Map<String, JSONObject> pay(JSONArray list) {
		PayMapper mapper = sqlSession.getMapper(PayMapper.class);
		PayExample example = new PayExample();
		for (Object obj : list) {
			JSONObject jo = (JSONObject) obj;
			Pay pay = new Pay();

			try {
				// 参数校验
				if (null == jo.getInteger("categoryId") || null == jo.getString("categoryName")) {
					throw new BusinessLogicRunTimeException("所有字段都不能为空!");
				}
				// 装载userbean
				pay.setPayId(jo.getInteger("payId"));
				pay.setName(jo.getString("payName"));
				pay.setNumber(jo.getString("number"));

				com.kingdee.eas.hrp.sms.model.PayExample.Criteria criteria = example.createCriteria();
				criteria.andPayIdEqualTo(jo.getInteger("payId"));

				List<Pay> pays = mapper.selectByExample(example);
				// 数据库存在记录即更新数据，反之插入数据
				if (null != pays && pays.size() == 1) {
					mapper.updateByExample(pay, example);
				} else {
					mapper.insert(pay);
				}
			} catch (BusinessLogicRunTimeException e) {
				// 参数校验失败，把该条数据放进异常数据返回
				eJson.put(e.toString(), jo);
			}
		}
		return eJson;
	}

	// 查询付款方式（list）
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.kingdee.eas.hrp.sms.service.impl.sys.ISyncService#getPayList(int, int)
	 */
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

	// 同步物料
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Map<String, JSONObject> item(JSONArray list) {
		ItemMapper mapper = sqlSession.getMapper(ItemMapper.class);
		ItemExample example = new ItemExample();
		for (Object obj : list) {
			JSONObject jo = (JSONObject) obj;
			Item item = new Item();

			try {
				// 参数校验
				if (null == jo.getInteger("categoryId") || null == jo.getString("categoryName")) {
					throw new BusinessLogicRunTimeException("所有字段都不能为空!");
				}
				// 装载userbean
				item.setItemId(jo.getInteger("itemId"));
				item.setName(jo.getString("itemName"));
				item.setNumber(jo.getString("number"));

				com.kingdee.eas.hrp.sms.model.ItemExample.Criteria criteria = example.createCriteria();
				criteria.andItemIdEqualTo(jo.getInteger("itemId"));

				List<Item> items = mapper.selectByExample(example);
				// 数据库存在记录即更新数据，反之插入数据
				if (null != items && items.size() == 1) {
					mapper.updateByExample(item, example);
				} else {
					mapper.insert(item);
				}
			} catch (BusinessLogicRunTimeException e) {
				// 参数校验失败，把该条数据放进异常数据返回
				eJson.put(e.toString(), jo);
			}
		}
		return eJson;
	}

	// 查询物料（list）
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.kingdee.eas.hrp.sms.service.impl.sys.ISyncService#getItemList(int, int)
	 */
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

	// 同步税种
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Map<String, JSONObject> taxCategory(JSONArray list) {
		TaxCategoryMapper mapper = sqlSession.getMapper(TaxCategoryMapper.class);
		TaxCategoryExample example = new TaxCategoryExample();
		for (Object obj : list) {
			JSONObject jo = (JSONObject) obj;
			TaxCategory taxCategory = new TaxCategory();

			try {
				// 参数校验
				if (null == jo.getInteger("categoryId") || null == jo.getString("categoryName")) {
					throw new BusinessLogicRunTimeException("所有字段都不能为空!");
				}
				// 装载userbean
				taxCategory.setTaxCategoryId(jo.getInteger("taxCategoryId"));
				taxCategory.setName(jo.getString("taxCategoryName"));
				taxCategory.setNumber(jo.getString("number"));

				com.kingdee.eas.hrp.sms.model.TaxCategoryExample.Criteria criteria = example.createCriteria();
				criteria.andTaxCategoryIdEqualTo(jo.getInteger("taxCategoryId"));

				List<TaxCategory> taxCategories = mapper.selectByExample(example);
				// 数据库存在记录即更新数据，反之插入数据
				if (null != taxCategories && taxCategories.size() == 1) {
					mapper.updateByExample(taxCategory, example);
				} else {
					mapper.insert(taxCategory);
				}
			} catch (BusinessLogicRunTimeException e) {
				// 参数校验失败，把该条数据放进异常数据返回
				eJson.put(e.toString(), jo);
			}
		}
		return eJson;
	}

	// 查询税种（list）
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.kingdee.eas.hrp.sms.service.impl.sys.ISyncService#getTaxCategoryList( int, int)
	 */
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

		Integer currencyId = currency.getCurrencyId();

		if (null == currencyId) {
			throw new BusinessLogicRunTimeException("内码为空");
		}

		CurrencyMapper mapper = sqlSession.getMapper(CurrencyMapper.class);
		CurrencyExample example = new CurrencyExample();
		Criteria criteria = example.createCriteria();
		criteria.andCurrencyIdEqualTo(currencyId);
		List<Currency> selectByExample = mapper.selectByExample(example);

		if (selectByExample.size() > 0) {
			// 已经存在
			mapper.updateByPrimaryKeySelective(currency);
		} else {
			// 插入
			mapper.insert(currency);
		}

	}
}
