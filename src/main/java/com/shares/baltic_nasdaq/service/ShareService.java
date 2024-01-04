package com.shares.baltic_nasdaq.service;

import com.shares.baltic_nasdaq.Model.Share;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


@Service
public class ShareService {
	public List<Share> convertNastaqResponse() {

		LocalDate date = LocalDate.now();
		WebClient webClient = WebClient.create("https://nasdaqbaltic.com/statistics/en/shares?date=" + date);
		Mono<String> response = webClient.get().retrieve().bodyToMono(String.class);
		Document doc = Jsoup.parse(response.block(), "UTF-8");

		List<Share> shares = new ArrayList<>();

		Element content = doc.getElementById("shares_form");
		Elements rows = content.getElementsByTag("tr");

		for (var row : rows) {
			Element td = row.select("td").first();
			if ((td == null) || (td.attr("data-sort-by").length() == 0)) {
				continue;
			}
			Elements tds = row.getElementsByTag("td");
			Share share = getShare(tds);
			shares.add(share);
		}
		return shares;
	}

	private Share getShare(Elements tds) {
		Share share = new Share();
		share.setCompany(tds.get(0).select("a").first().text());
		share.setTicker(tds.get(1).select("a").text());
		share.setLastPrice(getTdValue(tds, 2));
		share.setChange(getTdValue(tds, 3));
		share.setPercentage(getTdValue(tds, 4));
		share.setBid(getTdValue(tds, 5));
		share.setAsk(getTdValue(tds, 6));
		share.setTrades((int) getTdValue(tds, 7));
		share.setVolume((int) getTdValue(tds, 8));
		share.setTurnover(getTdValue(tds, 9));
		return share;
	}

	private double getTdValue(Elements tds, int index) {
		if (!isTdValueValid(tds, index)) {
			return 0;
		}
		if (index == 7 || index == 8 || index == 9) {
			return formatValueToLocaleDouble(tds, index);
		} else return parseValueToDouble(tds, index);
	}

	private double parseValueToDouble(Elements tds, int index) {
		return Double.parseDouble(tds.get(index).text());
	}

	private double formatValueToLocaleDouble(Elements tds, int index) {
		double result;
		try {
			result = NumberFormat.getNumberInstance(Locale.US).parse(tds.get(index).text()).doubleValue();
		} catch (ParseException e) {
			throw new NumberFormatException();
		}
		return result;
	}

	private boolean isTdValueValid(Elements tds, int index) {
		return (tds.get(index).text().trim().length() != 0)
				&& !"-".equals(tds.get(index).text().trim());
	}
}
