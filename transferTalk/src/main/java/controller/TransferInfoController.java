package controller;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import service.TransferService;
import vo.TransferVO;

/**
 * 해당리그 이적 조회 컨트롤러
 * TransferService에 leagueName을 매개변수로 하고 연도,시즌 조건 만족하는 selectTransferAll 메서드 필요
 * @param leagueName(리그이름)
 * @return transferInfo.jsp로 forward
 * 작성자 : 서준호
 */
public class TransferInfoController implements Controller {

	@Override
	public String execute(Map<String, Object> data) throws Exception {
		HttpServletRequest request = (HttpServletRequest) data.get("request");
		TransferService service = new TransferService();
		String leagueName = request.getParameter("leagueName");		
		List<TransferVO> transfer = service.selectTransferAll(leagueName);
		request.setAttribute("transferInfo", transfer);
				
		return "transferInfo.jsp";
	}
}
