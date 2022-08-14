package rs.miromaric.plutus.payment.model.payin;

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
 * Immutable implementation of {@link PayInResponse}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code ImmutablePayInResponse.builder()}.
 */
@Generated(from = "PayInResponse", generator = "Immutables")
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated("org.immutables.processor.ProxyProcessor")
public final class ImmutablePayInResponse
    implements PayInResponse, Serializable {
  private final String merchantRefNum;
  private final Date time;
  private final PayInStatus status;

  private ImmutablePayInResponse(
      String merchantRefNum,
      Date time,
      PayInStatus status) {
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
  public PayInStatus getStatus() {
    return status;
  }

  /**
   * Copy the current immutable object by setting a value for the {@link PayInResponse#getMerchantRefNum() merchantRefNum} attribute.
   * An equals check used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for merchantRefNum
   * @return A modified copy of the {@code this} object
   */
  public final ImmutablePayInResponse withMerchantRefNum(String value) {
    String newValue = Objects.requireNonNull(value, "merchantRefNum");
    if (this.merchantRefNum.equals(newValue)) return this;
    return new ImmutablePayInResponse(newValue, this.time, this.status);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link PayInResponse#getTime() time} attribute.
   * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for time
   * @return A modified copy of the {@code this} object
   */
  public final ImmutablePayInResponse withTime(Date value) {
    if (this.time == value) return this;
    Date newValue = Objects.requireNonNull(value, "time");
    return new ImmutablePayInResponse(this.merchantRefNum, newValue, this.status);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link PayInResponse#getStatus() status} attribute.
   * A value equality check is used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for status
   * @return A modified copy of the {@code this} object
   */
  public final ImmutablePayInResponse withStatus(PayInStatus value) {
    if (this.status == value) return this;
    PayInStatus newValue = Objects.requireNonNull(value, "status");
    if (this.status.equals(newValue)) return this;
    return new ImmutablePayInResponse(this.merchantRefNum, this.time, newValue);
  }

  /**
   * This instance is equal to all instances of {@code ImmutablePayInResponse} that have equal attribute values.
   * @return {@code true} if {@code this} is equal to {@code another} instance
   */
  @Override
  public boolean equals(Object another) {
    if (this == another) return true;
    return another instanceof ImmutablePayInResponse
        && equalTo((ImmutablePayInResponse) another);
  }

  private boolean equalTo(ImmutablePayInResponse another) {
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
   * Prints the immutable value {@code PayInResponse} with attribute values.
   * @return A string representation of the value
   */
  @Override
  public String toString() {
    return MoreObjects.toStringHelper("PayInResponse")
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
  @Generated(from = "PayInResponse", generator = "Immutables")
  @Deprecated
  @JsonDeserialize
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
  static final class Json
      implements PayInResponse, Serializable {

    private static final long serialVersionUID = 202107072355650000L;
    String merchantRefNum;
    Date time;
    PayInStatus status;
    @JsonProperty("merchantRefNum")
    public void setMerchantRefNum(String merchantRefNum) {
      this.merchantRefNum = merchantRefNum;
    }
    @JsonProperty("time")
    public void setTime(Date time) {
      this.time = time;
    }
    @JsonProperty("status")
    public void setStatus(PayInStatus status) {
      this.status = status;
    }
    @Override
    public String getMerchantRefNum() { throw new UnsupportedOperationException(); }
    @Override
    public Date getTime() { throw new UnsupportedOperationException(); }
    @Override
    public PayInStatus getStatus() { throw new UnsupportedOperationException(); }
  }

  /**
   * @param json A JSON-bindable data structure
   * @return An immutable value type
   * @deprecated Do not use this method directly, it exists only for the <em>Jackson</em>-binding infrastructure
   */
  @Deprecated
  @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
  static ImmutablePayInResponse fromJson(Json json) {
    ImmutablePayInResponse.Builder builder = ImmutablePayInResponse.builder();
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
   * Creates an immutable copy of a {@link PayInResponse} value.
   * Uses accessors to get values to initialize the new immutable instance.
   * If an instance is already immutable, it is returned as is.
   * @param instance The instance to copy
   * @return A copied immutable PayInResponse instance
   */
  public static ImmutablePayInResponse copyOf(PayInResponse instance) {
    if (instance instanceof ImmutablePayInResponse) {
      return (ImmutablePayInResponse) instance;
    }
    return ImmutablePayInResponse.builder()
        .from(instance)
        .build();
  }

  private static final long serialVersionUID = 202107072355650000L;

  /**
   * Creates a builder for {@link ImmutablePayInResponse ImmutablePayInResponse}.
   * <pre>
   * ImmutablePayInResponse.builder()
   *    .merchantRefNum(String) // required {@link PayInResponse#getMerchantRefNum() merchantRefNum}
   *    .time(Date) // required {@link PayInResponse#getTime() time}
   *    .status(rs.miromaric.plutus.payment.model.payin.PayInStatus) // required {@link PayInResponse#getStatus() status}
   *    .build();
   * </pre>
   * @return A new ImmutablePayInResponse builder
   */
  public static ImmutablePayInResponse.Builder builder() {
    return new ImmutablePayInResponse.Builder();
  }

  /**
   * Builds instances of type {@link ImmutablePayInResponse ImmutablePayInResponse}.
   * Initialize attributes and then invoke the {@link #build()} method to create an
   * immutable instance.
   * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
   * but instead used immediately to create instances.</em>
   */
  @Generated(from = "PayInResponse", generator = "Immutables")
  public static final class Builder {
    private static final long INIT_BIT_MERCHANT_REF_NUM = 0x1L;
    private static final long INIT_BIT_TIME = 0x2L;
    private static final long INIT_BIT_STATUS = 0x4L;
    private long initBits = 0x7L;

    private String merchantRefNum;
    private Date time;
    private PayInStatus status;

    private Builder() {
    }

    /**
     * Fill a builder with attribute values from the provided {@code PayInResponse} instance.
     * Regular attribute values will be replaced with those from the given instance.
     * Absent optional values will not replace present values.
     * @param instance The instance from which to copy values
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder from(PayInResponse instance) {
      Objects.requireNonNull(instance, "instance");
      merchantRefNum(instance.getMerchantRefNum());
      time(instance.getTime());
      status(instance.getStatus());
      return this;
    }

    /**
     * Initializes the value for the {@link PayInResponse#getMerchantRefNum() merchantRefNum} attribute.
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
     * Initializes the value for the {@link PayInResponse#getTime() time} attribute.
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
     * Initializes the value for the {@link PayInResponse#getStatus() status} attribute.
     * @param status The value for status 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("status")
    public final Builder status(PayInStatus status) {
      this.status = Objects.requireNonNull(status, "status");
      initBits &= ~INIT_BIT_STATUS;
      return this;
    }

    /**
     * Builds a new {@link ImmutablePayInResponse ImmutablePayInResponse}.
     * @return An immutable instance of PayInResponse
     * @throws java.lang.IllegalStateException if any required attributes are missing
     */
    public ImmutablePayInResponse build() {
      if (initBits != 0) {
        throw new IllegalStateException(formatRequiredAttributesMessage());
      }
      return new ImmutablePayInResponse(merchantRefNum, time, status);
    }

    private String formatRequiredAttributesMessage() {
      List<String> attributes = new ArrayList<>();
      if ((initBits & INIT_BIT_MERCHANT_REF_NUM) != 0) attributes.add("merchantRefNum");
      if ((initBits & INIT_BIT_TIME) != 0) attributes.add("time");
      if ((initBits & INIT_BIT_STATUS) != 0) attributes.add("status");
      return "Cannot build PayInResponse, some of required attributes are not set " + attributes;
    }
  }
}
