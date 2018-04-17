package payso.com.payso.model;

/**
 * A POJO for holding the carrier name and it's country code
 *
 * Created by mabdira on 1/1/18.
 */

public class CarrierItemData {

    private String carrierName;
    private int countryCode;

    public CarrierItemData(String carrierName, int countryCode) {
        this.carrierName = carrierName;
        this.countryCode = countryCode;
    }

    public String getCarrierName() {
        return carrierName;
    }

    public int getCountryCode() {
        return countryCode;
    }

    @Override
    public String toString() {
        return "CarrierItemData{" +
                "carrierName='" + carrierName + '\'' +
                ", countryCode=" + countryCode +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CarrierItemData that = (CarrierItemData) o;

        if (countryCode != that.countryCode) return false;
        return carrierName.equals(that.carrierName);
    }

    @Override
    public int hashCode() {
        int result = carrierName.hashCode();
        result = 31 * result + countryCode;
        return result;
    }
}
