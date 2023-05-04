package controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class TransferSummaryDetail implements Controller {

	@Override
	public String execute(Map<String, Object> data) throws Exception {
		HttpServletRequest request = (HttpServletRequest) data.get("request");
		String index = request.getParameter("index");
		request.setAttribute("index", index);
		return "/layout/summaryDetail.jsp";
	}

}
