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
import com.kingdee.eas.hrp.sms.dao.generate.CityMapper;
import com.kingdee.eas.hrp.sms.dao.generate.CountryMapper;
import com.kingdee.eas.hrp.sms.dao.generate.CountyMapper;
import com.kingdee.eas.hrp.sms.dao.generate.CurrencyMapper;
import com.kingdee.eas.hrp.sms.dao.generate.IndustryMapper;
import com.kingdee.eas.hrp.sms.dao.generate.ItemMapper;
import com.kingdee.eas.hrp.sms.dao.generate.PayMapper;
import com.kingdee.eas.hrp.sms.dao.generate.ProvinceMapper;
import com.kingdee.eas.hrp.sms.dao.generate.SettlementMapper;
import com.kingdee.eas.hrp.sms.dao.generate.SupplierMapper;
import com.kingdee.eas.hrp.sms.dao.generate.Supplier_License_TypeMapper;
import com.kingdee.eas.hrp.sms.dao.generate.TaxCategoryMapper;
import com.kingdee.eas.hrp.sms.dao.generate.UnitMapper;
import com.kingdee.eas.hrp.sms.exception.BusinessLogicRunTimeException;
import com.kingdee.eas.hrp.sms.model.Category;
import com.kingdee.eas.hrp.sms.model.CategoryExample;
import com.kingdee.eas.hrp.sms.model.Certificate;
import com.kingdee.eas.hrp.sms.model.CertificateExample;
import com.kingdee.eas.hrp.sms.model.City;
import com.kingdee.eas.hrp.sms.model.CityExample;
import com.kingdee.eas.hrp.sms.model.Country;
import com.kingdee.eas.hrp.sms.model.CountryExample;
import com.kingdee.eas.hrp.sms.model.County;
import com.kingdee.eas.hrp.sms.model.CountyExample;
import com.kingdee.eas.hrp.sms.model.Currency;
import com.kingdee.eas.hrp.sms.model.CurrencyExample;
import com.kingdee.eas.hrp.sms.model.CurrencyExample.Criteria;
import com.kingdee.eas.hrp.sms.model.Industry;
import com.kingdee.eas.hrp.sms.model.IndustryExample;
import com.kingdee.eas.hrp.sms.model.Item;
import com.kingdee.eas.hrp.sms.model.ItemExample;
import com.kingdee.eas.hrp.sms.model.Pay;
import com.kingdee.eas.hrp.sms.model.PayExample;
import com.kingdee.eas.hrp.sms.model.Province;
import com.kingdee.eas.hrp.sms.model.ProvinceExample;
import com.kingdee.eas.hrp.sms.model.Settlement;
import com.kingdee.eas.hrp.sms.model.SettlementExample;
import com.kingdee.eas.hrp.sms.model.Supplier;
import com.kingdee.eas.hrp.sms.model.SupplierExample;
import com.kingdee.eas.hrp.sms.model.Supplier_License_Type;
import com.kingdee.eas.hrp.sms.model.Supplier_License_TypeExample;
import com.kingdee.eas.hrp.sms.model.TaxCategory;
import com.kingdee.eas.hrp.sms.model.TaxCategoryExample;
import com.kingdee.eas.hrp.sms.model.Unit;
import com.kingdee.eas.hrp.sms.model.UnitExample;
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
	public List<Map<String, Object>> license(List<Supplier_License_Type> list) {

		// 同步失败的记录
		List<Map<String, Object>> errList = new ArrayList<Map<String, Object>>();

		for (Supplier_License_Type license_Type : list) {

			Map<String, Object> errItem = new HashMap<String, Object>(); // 记录错误信息

			try {
				sumbitLicense(license_Type);
			} catch (Exception e) {
				errItem.put("desc", e.getMessage());
				errItem.put("item", license_Type);
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
	private void sumbitLicense(Supplier_License_Type license_Type) {

		String license_Type_Id = license_Type.getId();

		if (null == license_Type_Id) {
			throw new BusinessLogicRunTimeException("内码为空");
		}

		Supplier_License_TypeMapper mapper = (Supplier_License_TypeMapper) sqlSession.getMapper(Supplier_License_Type.class);
		Supplier_License_TypeExample example = new Supplier_License_TypeExample();
		com.kingdee.eas.hrp.sms.model.Supplier_License_TypeExample.Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(license_Type_Id);
		List<Supplier_License_Type> selectByExample = mapper.selectByExample(example);

		if (selectByExample.size() > 0) {
			// 已经存在
			mapper.updateByPrimaryKeySelective(license_Type);
		} else {
			// 插入
			mapper.insert(license_Type);
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
	public List<Map<String, Object>> country(List<Country> list) {

		// 同步失败的记录
		List<Map<String, Object>> errList = new ArrayList<Map<String, Object>>();

		for (Country country : list) {

			Map<String, Object> errCountry = new HashMap<String, Object>(); // 记录错误信息

			try {
				sumbitCountry(country);
			} catch (Exception e) {
				errCountry.put("desc", e.getMessage());
				errCountry.put("country", country);
				errList.add(errCountry);
			}

		}
		return errList;
	}

	/**
	 * 提交一条数据到数据库，并提交事务
	 * 
	 * @Title sumbit
	 * @param country
	 *            void
	 * @date 2017-04-22 22:30:29 星期六
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	private void sumbitCountry(Country country) {

		String countryId = country.getId();

		if (null == countryId) {
			throw new BusinessLogicRunTimeException("内码为空");
		}

		CountryMapper mapper = sqlSession.getMapper(CountryMapper.class);
		CountryExample example = new CountryExample();
		com.kingdee.eas.hrp.sms.model.CountryExample.Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(countryId);
		List<Country> selectByExample = mapper.selectByExample(example);

		if (selectByExample.size() > 0) {
			// 已经存在
			mapper.updateByPrimaryKeySelective(country);
		} else {
			// 插入
			mapper.insert(country);
		}

	}

	@Override
	public List<Map<String, Object>> city(List<City> list) {

		// 同步失败的记录
		List<Map<String, Object>> errList = new ArrayList<Map<String, Object>>();

		for (City city : list) {

			Map<String, Object> errCity = new HashMap<String, Object>(); // 记录错误信息

			try {
				sumbitCity(city);
			} catch (Exception e) {
				errCity.put("desc", e.getMessage());
				errCity.put("city", city);
				errList.add(errCity);
			}

		}
		return errList;
	}

	/**
	 * 提交一条数据到数据库，并提交事务
	 * 
	 * @Title sumbit
	 * @param city
	 *            void
	 * @date 2017-04-22 22:30:29 星期六
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	private void sumbitCity(City city) {

		String cityId = city.getId();

		if (null == cityId) {
			throw new BusinessLogicRunTimeException("内码为空");
		}

		CityMapper mapper = sqlSession.getMapper(CityMapper.class);
		CityExample example = new CityExample();
		com.kingdee.eas.hrp.sms.model.CityExample.Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(cityId);
		List<City> selectByExample = mapper.selectByExample(example);

		if (selectByExample.size() > 0) {
			// 已经存在
			mapper.updateByPrimaryKeySelective(city);
		} else {
			// 插入
			mapper.insert(city);
		}

	}

	@Override
	public List<Map<String, Object>> province(List<Province> list) {

		// 同步失败的记录
		List<Map<String, Object>> errList = new ArrayList<Map<String, Object>>();

		for (Province province : list) {

			Map<String, Object> errProvince = new HashMap<String, Object>(); // 记录错误信息

			try {
				sumbitProvince(province);
			} catch (Exception e) {
				errProvince.put("desc", e.getMessage());
				errProvince.put("province", province);
				errList.add(errProvince);
			}

		}
		return errList;
	}

	/**
	 * 提交一条数据到数据库，并提交事务
	 * 
	 * @Title sumbit
	 * @param province
	 *            void
	 * @date 2017-04-22 22:30:29 星期六
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	private void sumbitProvince(Province province) {

		String provinceId = province.getId();

		if (null == provinceId) {
			throw new BusinessLogicRunTimeException("内码为空");
		}

		ProvinceMapper mapper = sqlSession.getMapper(ProvinceMapper.class);
		ProvinceExample example = new ProvinceExample();
		com.kingdee.eas.hrp.sms.model.ProvinceExample.Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(provinceId);
		List<Province> selectByExample = mapper.selectByExample(example);

		if (selectByExample.size() > 0) {
			// 已经存在
			mapper.updateByPrimaryKeySelective(province);
		} else {
			// 插入
			mapper.insert(province);
		}

	}

	@Override
	public List<Map<String, Object>> county(List<County> list) {

		// 同步失败的记录
		List<Map<String, Object>> errList = new ArrayList<Map<String, Object>>();

		for (County county : list) {

			Map<String, Object> errCounty = new HashMap<String, Object>(); // 记录错误信息

			try {
				sumbitCounty(county);
			} catch (Exception e) {
				errCounty.put("desc", e.getMessage());
				errCounty.put("county", county);
				errList.add(errCounty);
			}

		}
		return errList;
	}

	/**
	 * 提交一条数据到数据库，并提交事务
	 * 
	 * @Title sumbit
	 * @param county
	 *            void
	 * @date 2017-04-22 22:30:29 星期六
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	private void sumbitCounty(County county) {

		String countyId = county.getId();

		if (null == countyId) {
			throw new BusinessLogicRunTimeException("内码为空");
		}

		CountyMapper mapper = sqlSession.getMapper(CountyMapper.class);
		CountyExample example = new CountyExample();
		com.kingdee.eas.hrp.sms.model.CountyExample.Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(countyId);
		List<County> selectByExample = mapper.selectByExample(example);

		if (selectByExample.size() > 0) {
			// 已经存在
			mapper.updateByPrimaryKeySelective(county);
		} else {
			// 插入
			mapper.insert(county);
		}

	}
	
	@Override
	public List<Map<String, Object>> unit(List<Unit> list) {

		// 同步失败的记录
		List<Map<String, Object>> errList = new ArrayList<Map<String, Object>>();

		for (Unit unit : list) {

			Map<String, Object> errUnit = new HashMap<String, Object>(); // 记录错误信息

			try {
				sumbitUnit(unit);
			} catch (Exception e) {
				errUnit.put("desc", e.getMessage());
				errUnit.put("county", unit);
				errList.add(errUnit);
			}

		}
		return errList;
	}

	/**
	 * 提交一条数据到数据库，并提交事务
	 * 
	 * @Title sumbit
	 * @param county
	 *            void
	 * @date 2017-04-22 22:30:29 星期六
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	private void sumbitUnit(Unit unit) {

		String unitId = unit.getId();

		if (null == unitId) {
			throw new BusinessLogicRunTimeException("内码为空");
		}

		UnitMapper mapper = sqlSession.getMapper(UnitMapper.class);
		UnitExample example = new UnitExample();
		com.kingdee.eas.hrp.sms.model.UnitExample.Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(unitId);
		List<Unit> selectByExample = mapper.selectByExample(example);

		if (selectByExample.size() > 0) {
			// 已经存在
			mapper.updateByPrimaryKeySelective(unit);
		} else {
			// 插入
			mapper.insert(unit);
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
