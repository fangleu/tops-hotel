package com.travelzen.tops.hotel.chinaonline.utils;

import javax.xml.bind.JAXBElement;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

import org.apache.commons.lang3.StringUtils;

import cn.net.chinaonline.webservices._switch._1_5_1.reservation.CheckInPolicy;
import cn.net.chinaonline.webservices._switch._1_5_1.reservation.CreateBookingRequest;

import com.micros.webservices.og._4_3.common.Amount;
import com.micros.webservices.og._4_3.common.CreditCard;
import com.micros.webservices.og._4_3.common.Gender;
import com.micros.webservices.og._4_3.common.PersonName;
import com.micros.webservices.og._4_3.common.UniqueID;
import com.micros.webservices.og._4_3.common.UniqueIDType;
import com.micros.webservices.og._4_3.hotelcommon.AgeQualifyingCode;
import com.micros.webservices.og._4_3.hotelcommon.ArrayOfGuaranteeAccepted;
import com.micros.webservices.og._4_3.hotelcommon.ArrayOfRate;
import com.micros.webservices.og._4_3.hotelcommon.ArrayOfRatePlan;
import com.micros.webservices.og._4_3.hotelcommon.ArrayOfReservationComment;
import com.micros.webservices.og._4_3.hotelcommon.ArrayOfRoomRate;
import com.micros.webservices.og._4_3.hotelcommon.ArrayOfRoomStay;
import com.micros.webservices.og._4_3.hotelcommon.ArrayOfRoomType;
import com.micros.webservices.og._4_3.hotelcommon.CancelTerm;
import com.micros.webservices.og._4_3.hotelcommon.CancelTermType;
import com.micros.webservices.og._4_3.hotelcommon.Commission;
import com.micros.webservices.og._4_3.hotelcommon.Guarantee;
import com.micros.webservices.og._4_3.hotelcommon.GuaranteeAccepted;
import com.micros.webservices.og._4_3.hotelcommon.GuestCount;
import com.micros.webservices.og._4_3.hotelcommon.GuestCountList;
import com.micros.webservices.og._4_3.hotelcommon.HotelReference;
import com.micros.webservices.og._4_3.hotelcommon.Paragraph;
import com.micros.webservices.og._4_3.hotelcommon.Rate;
import com.micros.webservices.og._4_3.hotelcommon.RatePlan;
import com.micros.webservices.og._4_3.hotelcommon.ReservationComment;
import com.micros.webservices.og._4_3.hotelcommon.RoomRate;
import com.micros.webservices.og._4_3.hotelcommon.RoomStay;
import com.micros.webservices.og._4_3.hotelcommon.RoomType;
import com.micros.webservices.og._4_3.hotelcommon.TimeSpan;
import com.micros.webservices.og._4_3.name.ArrayOfNameAddress;
import com.micros.webservices.og._4_3.name.ArrayOfNamePhone;
import com.micros.webservices.og._4_3.name.ArrayOfProfile;
import com.micros.webservices.og._4_3.name.Customer;
import com.micros.webservices.og._4_3.name.NameAddress;
import com.micros.webservices.og._4_3.name.NamePhone;
import com.micros.webservices.og._4_3.name.Profile;
import com.micros.webservices.og._4_3.reservation.ArrayOfResGuest;
import com.micros.webservices.og._4_3.reservation.HotelReservation;
import com.micros.webservices.og._4_3.reservation.ResGuest;
import com.micros.webservices.ows._5_1.reservation.CancelBookingRequest;
import com.travelzen.framework.core.util.ValidationUtil;
import com.travelzen.tops.hotel.chinaonline.bean.CancelOrderRequest;
import com.travelzen.tops.hotel.chinaonline.bean.CreateOrderRequest;
import com.travelzen.tops.hotel.chinaonline.bean.base.GuestInformation;

public class ReservationConvert {

	public static CreateBookingRequest convertCreateOrderRequest(CreateOrderRequest request){

		CreateBookingRequest createBookingRequest = new CreateBookingRequest();
		HotelReservation hotelReservation = new HotelReservation();

		ArrayOfRoomStay arrayOfRoomStay = new ArrayOfRoomStay();
		RoomStay roomStay = new RoomStay();
		ArrayOfRatePlan arrayOfRatePlan = new ArrayOfRatePlan();
		RatePlan ratePlan = new RatePlan();
		Commission commission = new Commission();
		commission.setCurrencyCode("CNY");
		ratePlan.setCommission(commission);
		ratePlan.setRatePlanCode(request.getRateCode());    //
		ratePlan.setQualifyingIdType(HotelChinaOnlineConfigUtil.QUALIFYING_ID_TYPE);
		ratePlan.setQualifyingIdValue(HotelChinaOnlineConfigUtil.QUALIFYING_ID_VALUE);
		arrayOfRatePlan.getRatePlan().add(ratePlan);
		roomStay.setRatePlans(arrayOfRatePlan);

		ArrayOfRoomType arrayOfRoomType = new ArrayOfRoomType();
		RoomType roomType = new RoomType();
		roomType.setRoomTypeCode(request.getRoomCode()); 		//
		roomType.setNumberOfUnits(request.getRoomNumber());		//
		arrayOfRoomType.getRoomType().add(roomType);
		roomStay.setRoomTypes(arrayOfRoomType);

		ArrayOfRoomRate arrayOfRoomRate = new ArrayOfRoomRate();
		RoomRate roomRate = new RoomRate();
		roomRate.setRoomTypeCode(request.getRoomCode());
		roomRate.setRatePlanCode(request.getRateCode());
		ArrayOfRate arrayOfRate = new ArrayOfRate();
		for(int i = 0; i< request.getRate().size(); i++){
			Rate rate = new Rate();
			XMLGregorianCalendar  date = ChinaonlineConvertUtil.convertToXMLGregorianCalendar(request.getRate().get(i).getRateDate());
			rate.setEffectiveDate(date);
			Amount amount = new Amount();
			amount.setCurrencyCode("CNY");
			amount.setValue(request.getRate().get(i).getRate());
			rate.setBase(amount);
			arrayOfRate.getRate().add(rate);
		}
		roomRate.setRates(arrayOfRate);
		arrayOfRoomRate.getRoomRate().add(roomRate);
		roomStay.setRoomRates(arrayOfRoomRate);

		GuestCountList guestCountList = new GuestCountList();
		GuestCount guestCount = new GuestCount();
		guestCount.setAgeQualifyingCode(AgeQualifyingCode.ADULT);
		guestCount.setCount(1);
		guestCountList.getGuestCount().add(guestCount);
		roomStay.setGuestCounts(guestCountList);

		TimeSpan timeSpan = new TimeSpan();
		timeSpan.setStartDate(ChinaonlineConvertUtil.convertToXMLGregorianCalendar(request.getStartDate()));
		timeSpan.setEndDate(ChinaonlineConvertUtil.convertToXMLGregorianCalendar(request.getEndDate()));
		roomStay.setTimeSpan(timeSpan);

		Guarantee guarantee = new Guarantee();
		guarantee.setGuaranteeType(HotelChinaOnlineConfigUtil.GUARANTEE_TYPE);
		ArrayOfGuaranteeAccepted arrayOfGuaranteeAccepted = new ArrayOfGuaranteeAccepted();
		GuaranteeAccepted guaranteeAccepted = new GuaranteeAccepted();
		CreditCard creditCard = new CreditCard();
		guaranteeAccepted.setGuaranteeCreditCard(creditCard);
		arrayOfGuaranteeAccepted.getGuaranteeAccepted().add(guaranteeAccepted);
		guarantee.setGuaranteesAccepted(arrayOfGuaranteeAccepted);
		roomStay.setGuarantee(guarantee);

		HotelReference hotelReference = new HotelReference();
		hotelReference.setChainCode(HotelChinaOnlineConfigUtil.CHINCODE);
		hotelReference.setHotelCode(request.getHotelCode());
		roomStay.setHotelReference(hotelReference);

		ArrayOfReservationComment arrayOfReservationComment = new ArrayOfReservationComment();
		ReservationComment reservationComment = new ReservationComment();
		reservationComment.setGuestViewable(false);
		reservationComment.setCommentOriginatorCode("CRO");
		JAXBElement<String> name = new JAXBElement<String>(
				new QName(HotelChinaOnlineConfigUtil.COMMENT_NAME_SPACE, "Text"),
				String.class, request.getComments());
		reservationComment.getImageOrURLOrText().add(name);
		arrayOfReservationComment.getComment().add(reservationComment);
		roomStay.setComments(arrayOfReservationComment);

		arrayOfRoomStay.getRoomStay().add(roomStay);
		hotelReservation.setRoomStays(arrayOfRoomStay);

		ArrayOfResGuest arrayOfResGuest = new ArrayOfResGuest();
		ResGuest resGuest = new ResGuest();
		ArrayOfProfile arrayOfProfile = new ArrayOfProfile();

		for(int i =0; i<request.getGuestList().size(); i++){
			Profile profile = new Profile();
			Customer customer = new Customer();
			ArrayOfNameAddress arrayOfNameAddress = new ArrayOfNameAddress();
			NameAddress nameAddress = new NameAddress();
			ArrayOfNamePhone arrayOfNamePhone = new ArrayOfNamePhone();
			GuestInformation guest = request.getGuestList().get(i);
			customer.setGender(Gender.FEMALE);
			PersonName personName = new PersonName();
			personName.getNameTitle().add("Mr.");
			personName.setFirstName(guest.getName());
			personName.setLastName(ChinaonlineConvertUtil.chineseToPinyin(guest.getName()));
			customer.setPersonName(personName);

			nameAddress.setAddressType(guest.getAddressType());
			nameAddress.getAddressLine().add(guest.getAddressLine());
			nameAddress.setCityName(guest.getCityName());
			nameAddress.setStateProv(guest.getStateProv());
			nameAddress.setCountryCode(guest.getCountryCode());
			nameAddress.setPostalCode(guest.getPostCode());
			arrayOfNameAddress.getNameAddress().add(nameAddress);

			NamePhone namePhone = new NamePhone();
			namePhone.setPhoneRole("PHONE");
			namePhone.setPhoneType("MOBILE");
			namePhone.setPhoneNumber(guest.getMobile());
			arrayOfNamePhone.getNamePhone().add(namePhone);

			if (StringUtils.isNotBlank(guest.getEmail())) {
				NamePhone emailPhone = new NamePhone();
				emailPhone.setPhoneRole("EMAIL");
				emailPhone.setPhoneType("EMAIL");
				emailPhone.setPhoneNumber(guest.getEmail());
				arrayOfNamePhone.getNamePhone().add(emailPhone);
			}

			profile.setPhones(arrayOfNamePhone);
			profile.setAddresses(arrayOfNameAddress);
			profile.setCustomer(customer);

			arrayOfProfile.getProfile().add(profile);
		}
		
		resGuest.setProfiles(arrayOfProfile);
		arrayOfResGuest.getResGuest().add(resGuest);

		hotelReservation.setResGuests(arrayOfResGuest);
		createBookingRequest.setHotelReservation(hotelReservation);

		CheckInPolicy checkInPolicy = new CheckInPolicy();
		checkInPolicy.setEarliestTime(request.getEarliestCheckInTime());
		checkInPolicy.setLatestTime(request.getLastestCheckInTime());
		createBookingRequest.setCheckInPolicy(checkInPolicy);
		createBookingRequest.setChannelUniqueResID(request.getOrderId());

		return createBookingRequest;
	}

	public static boolean checkRequest(CreateOrderRequest request ) throws Exception{
		if(ValidationUtil.isNull(request, request.getOrderId(), request.getEarliestCheckInTime(),
			request.getHotelCode(), request.getEndDate(), request.getLastestCheckInTime(),
			request.getRateCode(), request.getRoomNumber(), request.getGuestList(),
			request.getRate()
			)) {
			throw new Exception("建单接口参数校验不通过");
		}
		return true;
	}
	
	public static CancelBookingRequest convertCancelOrderRequest(CancelOrderRequest request) {

		CancelBookingRequest cancelBookingRequest = new CancelBookingRequest();
		HotelReference hotelReference = new HotelReference();
		hotelReference.setHotelCode(request.getHotelCode());
		hotelReference.setChainCode("COL");
		cancelBookingRequest.setHotelReference(hotelReference);
		UniqueID uniqueID = new UniqueID();
		uniqueID.setType(UniqueIDType.INTERNAL);
		uniqueID.setValue(request.getHotelConfirmCode());
		cancelBookingRequest.setConfirmationNumber(uniqueID);
		cancelBookingRequest.setLastName(ChinaonlineConvertUtil.chineseToPinyin(request.getGuestName()));
		CancelTerm cancelTerm = new CancelTerm();
		cancelTerm.setCancelType(CancelTermType.CANCEL);
		cancelTerm.setCancelReasonCode("Cancel");
		Paragraph paragraph = new Paragraph();
		JAXBElement<String> name = new JAXBElement<String>(
				new QName(HotelChinaOnlineConfigUtil.COMMENT_NAME_SPACE, "Text"),
				String.class, request.getComments());
		paragraph.getImageOrURLOrText().add(name);
		cancelTerm.setCancelReason(paragraph);
		cancelBookingRequest.setCancelTerm(cancelTerm);
		return cancelBookingRequest;
	}
}
