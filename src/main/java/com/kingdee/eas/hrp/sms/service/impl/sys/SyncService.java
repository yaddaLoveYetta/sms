package com.kingdee.eas.hrp.sms.service.impl.sys;

import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kingdee.eas.hrp.sms.dao.generate.CategoryMapper;
import com.kingdee.eas.hrp.sms.dao.generate.CertificateMapper;
import com.kingdee.eas.hrp.sms.dao.generate.CurrencyMapper;
import com.kingdee.eas.hrp.sms.dao.generate.IndustryMapper;
import com.kingdee.eas.hrp.sms.dao.generate.ItemMapper;
import com.kingdee.eas.hrp.sms.dao.generate.PayMapper;
import com.kingdee.eas.hrp.sms.dao.generate.SettlementMapper;
import com.kingdee.eas.hrp.sms.dao.generate.SupplierMapper;
import com.kingdee.eas.hrp.sms.dao.generate.TaxCategoryMapper;
import com.kingdee.eas.hrp.sms.model.Category;
import com.kingdee.eas.hrp.sms.model.CategoryExample;
import com.kingdee.eas.hrp.sms.model.Certificate;
import com.kingdee.eas.hrp.sms.model.CertificateExample;
import com.kingdee.eas.hrp.sms.model.Currency;
import com.kingdee.eas.hrp.sms.model.CurrencyExample;
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

	// 同步供应商资料
	@Override
	public void supplier(JSONArray list) {
		SupplierMapper mapper = sqlSession.getMapper(SupplierMapper.class);
		SupplierExample example = new SupplierExample();
		Supplier supplier = new Supplier();
		for (Object obj : list) {
			JSONObject jo = (JSONObject) obj;

			supplier.setAddress(jo.getString("address"));
			supplier.setBanOrganization(jo.getString("banOrganization"));
			supplier.setBRNO(jo.getString("BRNO"));
			supplier.setCategoryID(jo.getInteger("categoryId"));
			supplier.setCity(jo.getString("city"));
			supplier.setCORP(jo.getString("CORP"));
			supplier.setCountry(jo.getString("country"));
			supplier.setCreateOrganization(jo.getString("createOrganization"));
			supplier.setIndustryID(jo.getString("industryId"));
			supplier.setProvince(jo.getString("province"));
			supplier.setShortName(jo.getString("shortName"));
			supplier.setStatus(jo.getInteger("status"));
			supplier.setSupplierId(jo.getInteger("supplierId"));
			supplier.setSupplierName(jo.getString("supplierName"));
			supplier.setTaxCategoryID(jo.getInteger("taxCategoryId"));
			supplier.setTaxID(jo.getInteger("taxId"));
			supplier.setTaxRate(jo.getInteger("taxRate"));

			com.kingdee.eas.hrp.sms.model.SupplierExample.Criteria criteria = example.createCriteria();
			criteria.andSupplierIdEqualTo(jo.getInteger("supplierId"));

			List<Supplier> suppliers = mapper.selectByExample(example);
			// 数据库存在记录即更新数据，反之插入数据
			if (null != suppliers && suppliers.size() == 1) {
				mapper.updateByExample(supplier, example);
			} else {
				mapper.insert(supplier);
			}
		}
	}

	// 同步分类
	@Override
	public void category(JSONArray list) {
		CategoryMapper mapper = sqlSession.getMapper(CategoryMapper.class);
		CategoryExample example = new CategoryExample();
		Category category = new Category();
		for (Object obj : list) {
			JSONObject jo = (JSONObject) obj;

			category.setCategoryId(jo.getInteger("categoryId"));
			category.setCategoryName(jo.getString("categoryName"));

			com.kingdee.eas.hrp.sms.model.CategoryExample.Criteria criteria = example.createCriteria();
			criteria.andCategoryIdEqualTo(jo.getInteger("categoryId"));

			List<Category> categories = mapper.selectByExample(example);
			// 数据库存在记录即更新数据，反之插入数据
			if (null != categories && categories.size() == 1) {
				mapper.updateByExample(category, example);
			} else {
				mapper.insert(category);
			}
		}
	}

	// 同步证书
	@Override
	public void certificate(JSONArray list) {
		CertificateMapper mapper = sqlSession.getMapper(CertificateMapper.class);
		CertificateExample example = new CertificateExample();
		Certificate certificate = new Certificate();
		for (Object obj : list) {
			JSONObject jo = (JSONObject) obj;

			certificate.setCertificateId(jo.getInteger("certificateId"));
			certificate.setCertificateName(jo.getString("certificateName"));

			com.kingdee.eas.hrp.sms.model.CertificateExample.Criteria criteria = example.createCriteria();
			criteria.andCertificateIdEqualTo(jo.getInteger("certificateId"));

			List<Certificate> certificates = mapper.selectByExample(example);
			// 数据库存在记录即更新数据，反之插入数据
			if (null != certificates && certificates.size() == 1) {
				mapper.updateByExample(certificate, example);
			} else {
				mapper.insert(certificate);
			}
		}
	}

	// 同步行业
	@Override
	public void industry(JSONArray list) {
		IndustryMapper mapper = sqlSession.getMapper(IndustryMapper.class);
		IndustryExample example = new IndustryExample();
		Industry industry = new Industry();
		for (Object obj : list) {
			JSONObject jo = (JSONObject) obj;

			industry.setIndustryId(jo.getInteger("industryId"));
			industry.setIndustryName(jo.getString("industryName"));

			com.kingdee.eas.hrp.sms.model.IndustryExample.Criteria criteria = example.createCriteria();
			criteria.andIndustryIdEqualTo(jo.getInteger("industryId"));

			List<Industry> industries = mapper.selectByExample(example);
			// 数据库存在记录即更新数据，反之插入数据
			if (null != industries && industries.size() == 1) {
				mapper.updateByExample(industry, example);
			} else {
				mapper.insert(industry);
			}
		}
	}

	// 同步币别
	@Override
	public void currency(JSONArray list) {
		CurrencyMapper mapper = sqlSession.getMapper(CurrencyMapper.class);
		CurrencyExample example = new CurrencyExample();
		Currency currency = new Currency();
		for (Object obj : list) {
			JSONObject jo = (JSONObject) obj;

			currency.setCurrencyId(jo.getInteger("currencyId"));
			currency.setCurrencyName(jo.getString("currencyName"));

			com.kingdee.eas.hrp.sms.model.CurrencyExample.Criteria criteria = example.createCriteria();
			criteria.andCurrencyIdEqualTo(jo.getInteger("currencyId"));

			List<Currency> currencies = mapper.selectByExample(example);
			// 数据库存在记录即更新数据，反之插入数据
			if (null != currencies && currencies.size() == 1) {
				mapper.updateByExample(currency, example);
			} else {
				mapper.insert(currency);
			}
		}
	}

	// 同步结算方式
	@Override
	public void settlement(JSONArray list) {
		SettlementMapper mapper = sqlSession.getMapper(SettlementMapper.class);
		SettlementExample example = new SettlementExample();
		Settlement settlement = new Settlement();
		for (Object obj : list) {
			JSONObject jo = (JSONObject) obj;

			settlement.setSettlementId(jo.getInteger("settlementId"));
			settlement.setSettlementName(jo.getString("settlementName"));

			com.kingdee.eas.hrp.sms.model.SettlementExample.Criteria criteria = example.createCriteria();
			criteria.andSettlementIdEqualTo(jo.getInteger("settlementId"));

			List<Settlement> settlements = mapper.selectByExample(example);
			// 数据库存在记录即更新数据，反之插入数据
			if (null != settlements && settlements.size() == 1) {
				mapper.updateByExample(settlement, example);
			} else {
				mapper.insert(settlement);
			}
		}
	}

	// 同步付款方式
	@Override
	public void pay(JSONArray list) {
		PayMapper mapper = sqlSession.getMapper(PayMapper.class);
		PayExample example = new PayExample();
		Pay pay = new Pay();
		for (Object obj : list) {
			JSONObject jo = (JSONObject) obj;

			pay.setPayId(jo.getInteger("payId"));
			pay.setPayName(jo.getString("payName"));

			com.kingdee.eas.hrp.sms.model.PayExample.Criteria criteria = example.createCriteria();
			criteria.andPayIdEqualTo(jo.getInteger("payId"));

			List<Pay> pays = mapper.selectByExample(example);
			// 数据库存在记录即更新数据，反之插入数据
			if (null != pays && pays.size() == 1) {
				mapper.updateByExample(pay, example);
			} else {
				mapper.insert(pay);
			}
		}
	}

	// 同步物料
	@Override
	public void item(JSONArray list) {
		ItemMapper mapper = sqlSession.getMapper(ItemMapper.class);
		ItemExample example = new ItemExample();
		Item item = new Item();
		for (Object obj : list) {
			JSONObject jo = (JSONObject) obj;

			item.setItemId(jo.getInteger("itemId"));
			item.setItemName(jo.getString("itemName"));

			com.kingdee.eas.hrp.sms.model.ItemExample.Criteria criteria = example.createCriteria();
			criteria.andItemIdEqualTo(jo.getInteger("itemId"));

			List<Item> items = mapper.selectByExample(example);
			// 数据库存在记录即更新数据，反之插入数据
			if (null != items && items.size() == 1) {
				mapper.updateByExample(item, example);
			} else {
				mapper.insert(item);
			}
		}
	}

	// 同步税种
	@Override
	public void taxCategory(JSONArray list) {
		TaxCategoryMapper mapper = sqlSession.getMapper(TaxCategoryMapper.class);
		TaxCategoryExample example = new TaxCategoryExample();
		TaxCategory taxCategory = new TaxCategory();
		for (Object obj : list) {
			JSONObject jo = (JSONObject) obj;

			taxCategory.setTaxCategoryId(jo.getInteger("taxCategoryId"));
			taxCategory.setTaxCategoryName(jo.getString("taxCategoryName"));

			com.kingdee.eas.hrp.sms.model.TaxCategoryExample.Criteria criteria = example.createCriteria();
			criteria.andTaxCategoryIdEqualTo(jo.getInteger("taxCategoryId"));

			List<TaxCategory> taxCategories = mapper.selectByExample(example);
			// 数据库存在记录即更新数据，反之插入数据
			if (null != taxCategories && taxCategories.size() == 1) {
				mapper.updateByExample(taxCategory, example);
			} else {
				mapper.insert(taxCategory);
			}
		}
	}

}
