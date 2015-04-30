<#macro guaranteeTemplate>
<div id="fillCardblock" class="fillCardblock" style="display: none;position: unset;">
<div class="assureTipsBox">
    <h5><b>担保提示：</b></h5>
    <p>
        房间保留至次日6:00，需冻结/暂扣${(ratePlan.guaranteeType)!}房费担保订单：金额￥${guaranteeAmount!"--"}元，结账后立即操作解冻/退款。${(ratePlan.cancelMsg)!}
    </p>
</div><br>
	<p>请填写担保信用卡：请确保信用卡证件登记信息与发卡行记录信息一致!</p>
	<table class="checkin_info_table">
		<colgroup>
			<col width="115" />
		</colgroup>
		<tbody>
			<tr>
				<th><em>*</em>持卡人姓名：</th>
				<td><input id="cardName" name="creditCard.holderName" type="text" style="width: 238px;"/></td>
			</tr>
			<tr>
				<th><em>*</em>发卡银行：</th>
				<td><input readonly id="cardBank" name="creditCard.bank" type="text" style="width: 238px;" value="招商银行"
					class="bankGather" code="1"/>
					<@createTemplate.bankList/>
				</td>
			</tr>
			<tr>
				<th><em>*</em>卡号：</th>
				<td><input id="cardNumber" name="creditCard.number" type="text" style="width: 133px;"/> 
				<span class="cardCVV">CVV码</span><input id="cardCVV" name="creditCard.cvv" type="text" style="width: 58px;"/>
				<span id="cardTip" class="message_ct" style="display:none"></span>
				</td>
			</tr>
			<tr>
				<th><em>*</em>信用卡有效期：</th>
				<td><input id="cardMonth" name="creditCard.expirationMonth" type="text" style="width: 58px; margin-right: 5px;" placeholder="XX"/><span>月</span>
					<input  id="cardYear" name="creditCard.expirationYear" type="text" style="width: 133px; margin: 0 5px;" placeholder="XXXX"/>年</td>
			</tr>
			<tr>
				<th><em>*</em>发卡银行预留证件：</th>
				<td><select style="width: 238px;"  id="cardType" name="creditCard.idType">
						<option value="IdentityCard" selected="">身份证</option>
						<option value="Passport">护照</option>
						<option value="Other">其他</option>
				</select></td>
			</tr>
			<tr>
				<th><em>*</em>证件号：</th>
				<td><input  id="cardNo" name="creditCard.idNo" type="text" style="width: 238px;"/></td>
			</tr>
		</tbody>

	</table>
</div>
</#macro>
