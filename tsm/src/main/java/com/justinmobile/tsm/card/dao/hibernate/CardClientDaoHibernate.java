package com.justinmobile.tsm.card.dao.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.justinmobile.core.dao.EntityDaoHibernate;
import com.justinmobile.tsm.application.domain.Application;
import com.justinmobile.tsm.application.domain.ApplicationClientInfo;
import com.justinmobile.tsm.card.dao.CardClientDao;
import com.justinmobile.tsm.card.domain.CardClient;
import com.justinmobile.tsm.card.domain.CardInfo;

@Repository("cardClientDao")
public class CardClientDaoHibernate extends EntityDaoHibernate<CardClient, Long> implements CardClientDao {
	@Override
	public List<ApplicationClientInfo> getClientByCard(CardInfo card) {

		StringBuilder hql = new StringBuilder();
		hql.append("select cc.client from ").append(CardClient.class.getName()).append(" as cc where cc.card = :card");
		Map<String, Object> values = new HashMap<String, Object>(1);
		values.put("card", card);
		return find(hql.toString(), values);
	}

	@Override
	public List<CardClient> getByCardAndApplication(CardInfo card, Application application) {
		String hql = "select cc from " + CardClient.class.getName()
				+ " as cc left join cc.client.applicationVersions as avs where avs.application = :application and cc.card = :card";

		Map<String, Object> values = new HashMap<String, Object>();
		values.put("card", card);
		values.put("application", application);

		return find(hql, values);
	}
}
