package com.mytaxi.service.driver;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.mytaxi.dataaccessobject.DriverRepository;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.OnlineStatus;
import com.mytaxi.exception.EntityNotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class DefaultDriverServiceTest {

	@Mock
	private DriverRepository driverRepository;

	@InjectMocks
	private DefaultDriverService defaultDriverService;

	@Test
	public void find_whenDataExist_shouldReturnData() {

		Mockito.when(driverRepository.findById(1l)).thenReturn(Optional.of(new DriverDO("user", "pass")));
		try {
			DriverDO find = defaultDriverService.find(1l);
			assertEquals("user", find.getUsername());

			assertEquals("pass", find.getPassword());
		} catch (EntityNotFoundException e) {

			e.printStackTrace();
		}

	}

	@Test(expected = EntityNotFoundException.class)
	public void find_whenDataDoNotExist_shouldThrowException() throws EntityNotFoundException {

		Mockito.when(driverRepository.findById(1l)).thenReturn(Optional.empty());
		DriverDO find = defaultDriverService.find(1l);

	}
	
	@Test
	public void findOnline_whenOnlineExists_shouldReturnData() {
		ArrayList<DriverDO> driverList = new ArrayList<DriverDO>();
		driverList.add(new DriverDO("user","pass"));
		driverList.add(new DriverDO("user1","pass2"));
		
		
		
		Mockito.when(driverRepository.findByOnlineStatus(OnlineStatus.ONLINE)).thenReturn(driverList);
		List<DriverDO> findOnline = defaultDriverService.findOnline(OnlineStatus.ONLINE);
		
		assertEquals(2, findOnline.size());
	}
	
	@Test
	public void findOnline_whenOnlineDoNotExists_shouldReturn() {
		ArrayList<DriverDO> driverList = new ArrayList<DriverDO>();
		driverList.add(new DriverDO("user","pass"));
		driverList.add(new DriverDO("user1","pass2"));
		
		
		
		Mockito.when(driverRepository.findByOnlineStatus(OnlineStatus.ONLINE)).thenReturn(Collections.EMPTY_LIST);
		List<DriverDO> findOnline = defaultDriverService.findOnline(OnlineStatus.ONLINE);
		
		assertEquals(0, findOnline.size());
	}

}
