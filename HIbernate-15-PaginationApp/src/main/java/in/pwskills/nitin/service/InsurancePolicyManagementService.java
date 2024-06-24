package in.pwskills.nitin.service;

import java.util.List;

import in.pwskills.nitin.entity.InsurancePolicyDTO;

public interface InsurancePolicyManagementService {
	public long fetchPagesCount(int pageSize);
	public List<InsurancePolicyDTO> fetchPageData(int pageSize,int pageNo);
}
