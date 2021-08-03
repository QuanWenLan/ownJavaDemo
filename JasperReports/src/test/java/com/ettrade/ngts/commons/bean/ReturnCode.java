package com.ettrade.ngts.commons.bean;

public enum ReturnCode {
    //common error
    NoErr("0", ""),
    InvalidUserOrPassword("1", ""),
    UserSuspended("2", ""),
    Unknown("3", ""),
    PwdExpired("5", ""),
    NewVersionAvailable("6", ""),
    ForceUpdate("7", ""),
    UnsupportedChannel("8", ""),
    Undefined("-1", ""),
    InvalidMessageFormat("-2", ""),
    SessionExpired("-3", ""),
    ServerNotReadyNotAllowLogin("-4", ""),
    ServerNotReadyNotAllowTrading("-5", ""),
    UnauthorizedOperation("-6", ""),
    ConsiderationWarning("-100", ""),

    //subIPO
    IPONotFound("IPO00200", "IPONotFound"),
    IPOHasBeenModified("IPO00201", "IPOHasBeenModified"),
    IPONotAvailableForSubscription("IPO00202", "IPONotAvailableForSubscription"),
    SubscriptionPeriodEnded("IPO00203", "SubscriptionPeriodEnded"),
    InvalidApplicationQuantity("IPO00204", "InvalidApplicationQuantity"),
    SubscribedIPOAlready("IPO00205", "SubscribedIPOAlready"),
    InvalidMarginRate("IPO00206", "InvalidMarginRate"),
    OnlyAllowSubscriptionWithMaxMarginRate("IPO00207", "OnlyAllowSubscriptionWithMaxMarginRate"),
    OnlyAllowCashSubscription("IPO00208", "OnlyAllowCashSubscription"),
    IPOFinancingLimitExceeded("IPO00209", "IPOFinancingLimitExceeded"),
    IPOIsPostponed("IPO00210", "IPOIsPostponed"),
    LoanAmountIsGreaterThanMaxLoanAmount("IPO00211", "LoanAmountIsGreaterThanMaxLoanAmount"),
    FinancingSybscriptionCannotBeCancelled("IPO00212", "FinancingSybscriptionCannotBeCancelled"),
    InvalidIPOStatusForCancellation("IPO00213", "InvalidIPOStatusForCancellation"),
    InvalidIPOOrFinancingPoolCurrency("IPO00214", "InvalidIPOOrFinancingPoolCurrency"),
    CurrencyAccountNotExists("IPO00215", "CurrencyAccountNotExists"),
    CannotCancelOutsideSubscribtionPeriod("IPO00216", "CannotCancelOutsideSubscribtionPeriod"),
    CannotViewHistoricalIPOForMoreThan365Days("IPO00217", "CannotViewHistoricalIPOForMoreThan365Days"),
    SubscriptionPeriodNotYetStarted("IPO00218", "SubscriptionPeriodNotYetStarted"),
    HoldFundManualReject("IPO00219", "HoldFundManualReject"),
    HoldFundInsufficientBalance("IPO00220", "HoldFundInsufficientBalance"),
    HoldFundError("IPO00221", "HoldFundError"),
    SuspendedClient("IPO00222", "SuspendedClient"),
    InvalidPayableAmt("IPO00223", "InvalidPayableAmt"),
    InvalidSubscriptionType("IPO00224", "InvalidSubscriptionType"),

    //ipo maintenance
    IPONotFoundInStaging("IPO00100", "IPONotFoundInStaging"),
    IPOHasBeenModifiedInStaging("IPO00101", "IPOHasBeenModifiedInStaging"),
    IPOHasBeenDeleted("IPO00102", "IPOHasBeenDeleted"),
    IPOAlreadyExists("IPO00103", "IPOAlreadyExists"),
    InvalidIPOApprovalStatus("IPO00104", "InvalidIPOApprovalStatus"),
    InvalidEmptyFields("IPO00105", "InvaildEmptyFields"),
    FinancingPoolNotFound("IPO00106", "FinancingPoolNotFound"),
    FinancingPoolHasBeenDeleted("IPO00107", "FinancingPoolHasBeenDeleted"),
    FinancingPoolIsInUse("IPO00108", "FinancingPoolIsInUse"),
    FinancingPoolExists("IPO00109", "FinancingPoolExists"),
    IPONotSupportedInFinancingPool("IPO00110", "IPONotSupportedInFinancingPool"),
    InvalidSubscriptionStartOrCloseTime("IPO00111", "InvalidSubscriptionStartOrCloseTime"),
    InvalidOfferStartOrCloseTime("IPO00112", "InvalidOfferStartOrCloseTime"),
    InvalidOfferStartOrSubscriptionStartTime("IPO00113", "InvalidOfferStartOrSubscriptionStartTime"),
    InvalidSubscriptionCloseTimeOrOfferCloseTime("IPO00114", "InvalidSubscriptionCloseTimeOrOfferCloseTime"),
    InvalidOfferClosetimeOrListingDate("IPO00115", "InvalidOfferClosetimeOrListingDate"),
    InvalidOfferPriceFromOrTo("IPO00116", "InvalidOfferPriceFromOrTo"),
    InvalidMarginStartOrCloseTime("IPO00117", "InvalidMarginStartOrCloseTime"),
    InvalidSubscriptionStartTimeOrMarginStartTime("IPO00118", "InvalidSubscriptionStartTimeOrMarginStartTime"),
    InvalidMarginCloseTimeOrSubscriptionCloseTime("IPO00119", "InvalidMarginCloseTimeOrSubscriptionCloseTime"),
    InvalidMaxMarginRate("IPO00120", "InvalidMaxMarginRate"),
    InvalidFinancingPoolStartOrEndDate("IPO00121", "InvalidFinancingPoolStartOrEndDate"),
    TotalValueCannotBeSmallerThanTheUsedValue("IPO00122", "TotalValueCannotBeSmallerThanTheUsedValue"),
    InvalidFinancingPoolapprovalStatus("IPO00123", "InvalidFinancingPoolapprovalStatus"),
    InvalidQuantityAmountRange("IPO00124", "InvalidQuantityAmountRange"),
    InvalidTotalOfIPOCutOff("IPO00125", "InvalidTotalOfIPOCutOff"),
    InvalidTotalOfIPOAllot("IPO00126", "InvalidTotalOfIPOAllot"),
    ErrorInSaveAmtIntRate("IPO00127", "ErrorInSaveAmtIntRate"),
    ErrorInSaveAppQtyAmt("IPO00128", "ErrorInSaveAppQtyAmt"),
    ErrorInSaveAppQtyAmtRange("IPO00129", "ErrorInSaveAppQtyAmtRange"),
    InvalidSecurityIdFormat("IPO00131", "InvalidSecurityIdFormat"),
    InvalidIPOStatus("IPO00132", "InvalidIPOStatus"),
    NewTotalValueCannotBeSmallerThanTheOriginalOne("IPO00133", "NewTotalValueCannotBeSmallerThanTheOriginalOne"),
    InvalidIPOCurrency("IPO00134", "InvalidIPOCurrency"),
    CurrencyCannotBeChangedWhenThereAreSubscriptions("IPO00135", "CurrencyCannotBeChangedWhenThereAreSubscriptions"),
    HoldFundORAllowCancelSettingCannotBeChangedWhenThereAreSubscriptions("IPO00136", "HoldFundORAllowCancelSettingCannotBeChangedWhenThereAreSubscriptions"),
    IntervalInCalculationMethodNeedsToBeMultipleOfBroadLot("IPO00137", "IntervalInCalculationMethodNeedsToBeMultipleOfBroadLot"),
    TemplateNameAlreadyExists("IPO00138", "TemplateNameAlreadyExists"),
    InvalidAnnoucementDateOrOfferCloseTime("IPO00139", "InvalidAnnoucementDateOrOfferCloseTime"),
    InvalidPriceFixingDateOrOfferCloseTime("IPO00140", "InvalidPriceFixingDateOrOfferCloseTime"),
    InvalidDispatchOfSharesRefundDateOrOfferCloseTime("IPO00141", "InvalidDispatchOfSharesRefundDateOrOfferCloseTime"),
    InvalidAnnoucementDateOrListingDate("IPO00142", "InvalidAnnoucementDateOrListingDate"),
    InvalidPriceFixingDateOrListingDate("IPO00143", "InvalidPriceFixingDateOrListingDate"),
    InvalidDispatchOfSharesRefundDateOrListingDate("IPO00144", "InvalidDispatchOfSharesRefundDateOrListingDate"),
    IPOCannotBeDeletedWhenThereAreSubscriptions("IPO00145", "IPOCannotBeDeletedWhenThereAreSubscriptions"),
    InvalidInputNumber("IPO00146", "InvalidInputNumber"),
    GlobalOfferingMustBeLargerOrEqualToPublicOffer("IPO00147", "GlobalOfferingMustBeLargerOrEqualToPublicOffer"),
    FailToDownloadSummary("IPO00148", "FailToDownloadSummary"),
    InvalidMarginRateFilter("IPO00149", "InvalidMarginRateFilter"),
    NominalValueIsInvalid("IPO00150", "NominalValueIsInvalid"),
    InvalidTotalOfDividendBonusRecord("IPO00151", "InvalidTotalOfDividendBonusRecord"),

    //Fund Stock In Out
    FundStockRequestNotFound("ACC00100", "FundStockRequestNotFound"),
    ClientBankInfoNotFound("ACC00101", "ClientBankInfoNotFound"),
    ExchangeIdOrSecurityIdNotFound("ACC00102", "ExchangeIdOrSecurityIdNotFound"),
    InvalidInputLengthOfRemark("ACC00103", "InvalidInputLengthOfRemark"),
    InvalidInputAmount("ACC00104", "InvalidInputAmount"),
    InvalidInputTransactionType("ACC00105", "InvalidInputTransactionType"),
    InvalidInputQuantity("ACC00106", "InvalidInputQuantity"),
    InvalidInputStockWithdrawalType("ACC00107", "InvalidInputStockWithdrawalType"),
    InvalidInputLengthOfReason("ACC00108", "InvalidInputLengthOfReason"),
    InvalidFundStockStatus("ACC00109", "InvalidFundStockStatus"),
    InvalidInputFundStockStatus("ACC00110", "InvalidInputFundStockStatus"),
    CorpBankInfoNotFound("ACC00111", "CorpBankInfoNotFound"),
    CannotViewHistoricalAccTransForMoreThan365Days("ACC00112", "CannotViewHistoricalAccTransForMoreThan365Days"),
    ClientBankPaySlipNotFound("ACC00113", "ClientBankPaySlipNotFound"),
    FailToDownloadAccTransSummary("ACC00114", "FailToDownloadAccTransSummary"),
    FundDepReqIdNotFound("ACC00115", "FundDepReqIdNotFound"),
    InvalidBankPaySlipFileExtension("ACC00116", "InvalidBankPaySlipFileExtension"),
    ErrorInUploadPaySlip("ACC00117", "ErrorInUploadPaySlip"),

    //Statement
    ErrorInUploadStatement("STM00100", "ErrorInUploadStatement"),
    FileNotExists("STM00101", "FileNotExists"),
    ErrorInStorageRecordStatus("STM00102", "ErrorInStorageRecordStatus"),
    ErrorInDocMasterRecordStatus("STM00103", "ErrorInDocMasterRecordStatus"),
    ErrorInDocRecordStatus("STM00104", "ErrorInDocRecordStatus"),
    FileExceedsOnlineWindowDay("STM00105", "FileExceedsOnlineWindowDay"),
    ErrorInUploadKeyFormat("STM00106", "ErrorInUploadKeyFormat"),
    DocMasterRecordTypeNotFound("STM00107", "DocMasterRecordTypeNotFound"),
    DocRecordTimeTypeNotFound("STM00108", "DocRecordTimeTypeNotFound"),
    StorageRecordNotFound("STM00109", "StorageRecordNotFound"),
    InvalidEmptyField("STM00110", "InvalidEmptyField"),
    InvalidStmtFileExtension("STM00111", "InvalidStmtFileExtension"),
    FileUploadIsStopped("STM00112", "FileUploadIsStopped"),
    InvalidCurrency("STM00113", "InvalidCurrency"),
    ErrorInCreateOrLoadKey("STM00114", "ErrorInCreateOrLoadKey"),
    ErrorInEncryptOrDecryptFile("STM00115", "ErrorInEncryptOrDecryptFile"),
    AccTypeNotFound("STM00116", "ErrorInEncryptOrDecryptFile"),
    InvalidIsBackDateInput("STM00117", "InvalidIsBackDateInput"),

    //Corporate Action
    ScripDividendRecordCouldNotBeFound("COR00100", "ScripDividendRecordCouldNotBeFound"),
    TotalInputQuantityIsNotTheSameAsBookCloseQuantity("COR00101", "TotalInputQuantityIsNotTheSameAsBookCloseQuantity"),
    InvalidCashDividendCurrency("COR00102", "InvalidCashDividendCurrency"),
    ScripDividendMasterRecordCouldNotBeFound("COR00103", "ScripDividendMasterRecordCouldNotBeFound"),
    ScripDividendReplyPeriodHasEnded("COR00104", "ScripDividendReplyPeriodHasEnded"),
    TheScripDividendRecordCouldNotBeFound("COR00105", "TheScripDividendRecordCouldNotBeFound"),
    ScripDividendReplyPeriodIsNotStarted("COR00106", "ScripDividendReplyPeriodIsNotStarted"),
    InvalidTotalNumberOfScripDividendMasterRecords("COR00107", "InvalidTotalNumberOfScripDividendMasterRecords"),
    InvalidTotalNumberOfScripDividendRecords("COR00108", "InvalidTotalNumberOfScripDividendRecords"),

    // Chat Action
    SynapseInfoNotFoundError("CHT00001", "SynapseInfoNotFoundError"),
    SynapseUserNotFound("CHT00002", "SynapseUserNotFound"), // error in getting corresponding user in database
    SynapseServerUserOrPasswordError("CHT00003", "SynapseServerUserOrPasswordError"), // error from synapse
    SynapseServerConnectionError("CHT00004", "SynapseServerConnectionError"),
    SynapseUserAlreadyExists("CHT00005", "SynapseUserAlreadyExists"), // user already exists in database, do not create again
    SynapseUnhandledError("CHT00006", "SynapseUnhandledError"),
    SynapseSystemUserNotFound("CHT00007", "SynapseSystemUserNotFound"),
    SynapseCreateNotificationRoomError("CHT00008", "SynapseCreateNotificationRoomError"),
    SynapseNotificationRoomNotFoundError("CHT00009", "SynapseNotificationRoomNotFoundError"),
    SynapseSystemUserLoginError("CHT00010", "SynapseSystemUserLoginError"),
    SynapseNoAdminRightError("CHT00011", "SynapseNoAdminRightError"),
    SynapseInvalidUserRole("CHT00012", "SynapseInvalidUserRole"),
    SynapseInvalidData("CHT00013", "SynapseInvalidData"),
    SynapseLoginInfoError("CHT00014", "SynapseLoginInfoError"),
    SynapseOfficialIdCrashError("CHT00015", "SynapseOfficialIdCrashError"),
    SynapseOfficialIdNotExistError("CHT00016", "SynapseOfficialIdNotExistError"),
    SynapseDataBaseStorageException("CHT00017", "SynapseDataBaseStorageException"),
    SynapseOfficialIdWasDeletedError("CHT00018", "SynapseOfficialIdWasDeletedError"),
    SynpseOfficialIdSpecialFlagNotMatchError("CHT00019", "SynpseOfficialIdSpecialFlagNotMatchError"),
    SynpseNoOfficialIdToSyncError("CHT00020", "SynpseNoOfficialIdToSyncError"),
    SynpseUserIdFormattingError("CHT00021", "SynpseUserIdFormattingError"),
    SynapseDatabaseInternalError("CHT00022", "SynapseDatabaseInternalError"),
    ChatServerConnectionError("CHT00112", "ChatServerConnectionError"),
    ChatServerInternalError("CHT00113", "ChatServerInternalError"),
    InvalidInput("CHT00115", "InvalidInput"),

    // Market Data
    CompletableFutureOnlySupportSnapshot("MA00001", "CompletableFutureOnlySupportSnapshot"),

    // JasperReport
    NoReportSupplied("JR00001", "NoReportSupplied"),
    NoFileTypeSupplied("JR00002", "NoFileTypeSupplied"),
    NoFilterSupplied("JR00003", "NoFilterSupplied"),
    NoEpIdSupplied("JR00004", "NoEpIdSupplied"),
    NoDateFilterSupplied("JR00005", "NoDateFilterSupplied"),
    NoSortingSupplied("JR00006", "NoSortingSupplied"),
    InvalidFormatSupplied("JR10000", "InvalidFormatSupplied"),
    InvalidReportSupplied("JR10001", "InvalidReportSupplied"),
    InvalidFileTypeSupplied("JR10002", "InvalidFileTypeSupplied"),
    InvalidDateFormat("JR10003", "InvalidDateFormat"),
    InvalidSortingField("JR10004", "InvalidSortingField"),
    InvalidQuantityField("JR10005", "InvalidQuantityField"),
    InvalidAccountTypeField("JR10006", "InvalidAccountTypeField"),
    JasperReportGenerationError("JR20001", "JasperReportGenerationError"),
    TServerDBNotFound("JR20002", "TServerDBNotFound"),;

    private String value;
    private String message;

    ReturnCode(String value, String message) {
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }

    public static ReturnCode getReturnCode(String value) {
        for (ReturnCode code : ReturnCode.values()) {
            if (code.getValue().equals(value)) {
                return code;
            }
        }
        return ReturnCode.Undefined;
    }
}
