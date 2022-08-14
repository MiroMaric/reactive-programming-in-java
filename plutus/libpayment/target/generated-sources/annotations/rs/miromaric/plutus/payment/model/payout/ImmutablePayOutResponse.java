package rs.miromaric.plutus.payment.model.payout;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.MoreObjects;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import org.immutables.value.Generated;

/**
 * Immutable implementation of {@link PayOutResponse}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code ImmutablePayOutResponse.builder()}.
 */
@Generated(from = "PayOutResponse", generator = "Immutables")
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated("org.immutables.processor.ProxyProcessor")
public final class ImmutablePayOutResponse
    implements PayOutResponse, Serializable {
  private final String merchantRefNum;
  private final Date time;
  private final PayOutStatus status;

  private ImmutablePayOutResponse(
      String merchantRefNum,
      Date time,
      PayOutStatus status) {
    this.merchantRefNum = merchantRefNum;
    this.time = time;
    this.status = status;
  }

  /**
   * @return The value of the {@code merchantRefNum} attribute
   */
  @JsonProperty("merchantRefNum")
  @Override
  public String getMerchantRefNum() {
    return merchantRefNum;
  }

  /**
   * @return The value of the {@code time} attribute
   */
  @JsonProperty("time")
  @Override
  public Date getTime() {
    return time;
  }

  /**
   * @return The value of the {@code status} attribute
   */
  @JsonProperty("status")
  @Override
  public PayOutStatus getStatus() {
    return status;
  }

  /**
   * Copy the current immutable object by setting a value for the {@link PayOutResponse#getMerchantRefNum() merchantRefNum} attribute.
   * An equals check used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for merchantRefNum
   * @return A modified copy of the {@code this} object
   */
  public final ImmutablePayOutResponse withMerchantRefNum(String value) {
    String newValue = Objects.requireNonNull(value, "merchantRefNum");
    if (this.merchantRefNum.equals(newValue)) return this;
    return new ImmutablePayOutResponse(newValue, this.time, this.status);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link PayOutResponse#getTime() time} attribute.
   * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for time
   * @return A modified copy of the {@code this} object
   */
  public final ImmutablePayOutResponse withTime(Date value) {
    if (this.time == value) return this;
    Date newValue = Objects.requireNonNull(value, "time");
    return new ImmutablePayOutResponse(this.merchantRefNum, newValue, this.status);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link PayOutResponse#getStatus() status} attribute.
   * A value equality check is used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for status
   * @return A modified copy of the {@code this} object
   */
  public final ImmutablePayOutResponse withStatus(PayOutStatus value) {
    if (this.status == value) return this;
    PayOutStatus newValue = Objects.requireNonNull(value, "status");
    if (this.status.equals(newValue)) return this;
    return new ImmutablePayOutResponse(this.merchantRefNum, this.time, newValue);
  }

  /**
   * This instance is equal to all instances of {@code ImmutablePayOutResponse} that have equal attribute values.
   * @return {@code true} if {@code this} is equal to {@code another} instance
   */
  @Override
  public boolean equals(Object another) {
    if (this == another) return true;
    return another instanceof ImmutablePayOutResponse
        && equalTo((ImmutablePayOutResponse) another);
  }

  private boolean equalTo(ImmutablePayOutResponse another) {
    return merchantRefNum.equals(another.merchantRefNum)
        && time.equals(another.time)
        && status.equals(another.status);
  }

  /**
   * Computes a hash code from attributes: {@code merchantRefNum}, {@code time}, {@code status}.
   * @return hashCode value
   */
  @Override
  public int hashCode() {
    int h = 5381;
    h += (h << 5) + merchantRefNum.hashCode();
    h += (h << 5) + time.hashCode();
    h += (h << 5) + status.hashCode();
    return h;
  }

  /**
   * Prints the immutable value {@code PayOutResponse} with attribute values.
   * @return A string representation of the value
   */
  @Override
  public String toString() {
    return MoreObjects.toStringHelper("PayOutResponse")
        .omitNullValues()
        .add("merchantRefNum", merchantRefNum)
        .add("time", time)
        .add("status", status)
        .toString();
  }

  /**
   * Utility type used to correctly read immutable object from JSON representation.
   * @deprecated Do not use this type directly, it exists only for the <em>Jackson</em>-binding infrastructure
   */
  @Generated(from = "PayOutResponse", generator = "Immutables")
  @Deprecated
  @JsonDeserialize
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
  static final class Json
      implements PayOutResponse, Serializable {

    private static final long serialVersionUID = 202107072355650000L;
    String merchantRefNum;
    Date time;
    PayOutStatus status;
    @JsonProperty("merchantRefNum")
    public void setMerchantRefNum(String merchantRefNum) {
      this.merchantRefNum = merchantRefNum;
    }
    @JsonProperty("time")
    public void setTime(Date time) {
      this.time = time;
    }
    @JsonProperty("status")
    public void setStatus(PayOutStatus status) {
      this.status = status;
    }
    @Override
    public String getMerchantRefNum() { throw new UnsupportedOperationException(); }
    @Override
    public Date getTime() { throw new UnsupportedOperationException(); }
    @Override
    public PayOutStatus getStatus() { throw new UnsupportedOperationException(); }
  }

  /**
   * @param json A JSON-bindable data structure
   * @return An immutable value type
   * @deprecated Do not use this method directly, it exists only for the <em>Jackson</em>-binding infrastructure
   */
  @Deprecated
  @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
  static ImmutablePayOutResponse fromJson(Json json) {
    ImmutablePayOutResponse.Builder builder = ImmutablePayOutResponse.builder();
    if (json.merchantRefNum != null) {
      builder.merchantRefNum(json.merchantRefNum);
    }
    if (json.time != null) {
      builder.time(json.time);
    }
    if (json.status != null) {
      builder.status(json.status);
    }
    return builder.build();
  }

  /**
   * Creates an immutable copy of a {@link PayOutResponse} value.
   * Uses accessors to get values to initialize the new immutable instance.
   * If an instance is already immutable, it is returned as is.
   * @param instance The instance to copy
   * @return A copied immutable PayOutResponse instance
   */
  public static ImmutablePayOutResponse copyOf(PayOutResponse instance) {
    if (instance instanceof ImmutablePayOutResponse) {
      return (ImmutablePayOutResponse) instance;
    }
    return ImmutablePayOutResponse.builder()
        .from(instance)
        .build();
  }

  private static final long serialVersionUID = 202107072355650000L;

  /**
   * Creates a builder for {@link ImmutablePayOutResponse ImmutablePayOutResponse}.
   * <pre>
   * ImmutablePayOutResponse.builder()
   *    .merchantRefNum(String) // required {@link PayOutResponse#getMerchantRefNum() merchantRefNum}
   *    .time(Date) // required {@link PayOutResponse#getTime() time}
   *    .status(rs.miromaric.plutus.payment.model.payout.PayOutStatus) // required {@link PayOutResponse#getStatus() status}
   *    .build();
   * </pre>
   * @return A new ImmutablePayOutResponse builder
   */
  public static ImmutablePayOutResponse.Builder builder() {
    return new ImmutablePayOutResponse.Builder();
  }

  /**
   * Builds instances of type {@link ImmutablePayOutResponse ImmutablePayOutResponse}.
   * Initialize attributes and then invoke the {@link #build()} method to create an
   * immutable instance.
   * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
   * but instead used immediately to create instances.</em>
   */
  @Generated(from = "PayOutResponse", generator = "Immutables")
  public static final class Builder {
    private static final long INIT_BIT_MERCHANT_REF_NUM = 0x1L;
    private static final long INIT_BIT_TIME = 0x2L;
    private static final long INIT_BIT_STATUS = 0x4L;
    private long initBits = 0x7L;

    private String merchantRefNum;
    private Date time;
    private PayOutStatus status;

    private Builder() {
    }

    /**
     * Fill a builder with attribute values from the provided {@code PayOutResponse} instance.
     * Regular attribute values will be replaced with those from the given instance.
     * Absent optional values will not replace present values.
     * @param instance The instance from which to copy values
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder from(PayOutResponse instance) {
      Objects.requireNonNull(instance, "instance");
      merchantRefNum(instance.getMerchantRefNum());
      time(instance.getTime());
      status(instance.getStatus());
      return this;
    }

    /**
     * Initializes the value for the {@link PayOutResponse#getMerchantRefNum() merchantRefNum} attribute.
     * @param merchantRefNum The value for merchantRefNum 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("merchantRefNum")
    public final Builder merchantRefNum(String merchantRefNum) {
      this.merchantRefNum = Objects.requireNonNull(merchantRefNum, "merchantRefNum");
      initBits &= ~INIT_BIT_MERCHANT_REF_NUM;
      return this;
    }

    /**
     * Initializes the value for the {@link PayOutResponse#getTime() time} attribute.
     * @param time The value for time 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("time")
    public final Builder time(Date time) {
      this.time = Objects.requireNonNull(time, "time");
      initBits &= ~INIT_BIT_TIME;
      return this;
    }

    /**
     * Initializes the value for the {@link PayOutResponse#getStatus() status} attribute.
     * @param status The value for status 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("status")
    public final Builder status(PayOutStatus status) {
      this.status = Objects.requireNonNull(status, "status");
      initBits &= ~INIT_BIT_STATUS;
      return this;
    }

    /**
     * Builds a new {@link ImmutablePayOutResponse ImmutablePayOutResponse}.
     * @return An immutable instance of PayOutResponse
     * @throws java.lang.IllegalStateException if any required attributes are missing
     */
    public ImmutablePayOutResponse build() {
      if (initBits != 0) {
        throw new IllegalStateException(formatRequiredAttributesMessage());
      }
      return new ImmutablePayOutResponse(merchantRefNum, time, status);
    }

    private String formatRequiredAttributesMessage() {
      List<String> attributes = new ArrayList<>();
      if ((initBits & INIT_BIT_MERCHANT_REF_NUM) != 0) attributes.add("merchantRefNum");
      if ((initBits & INIT_BIT_TIME) != 0) attributes.add("time");
      if ((initBits & INIT_BIT_STATUS) != 0) attributes.add("status");
      return "Cannot build PayOutResponse, some of required attributes are not set " + attributes;
    }
  }
}
