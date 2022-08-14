package rs.miromaric.plutus.provider.model.debit;

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
import rs.miromaric.plutus.provider.model.PaymentResponse;

/**
 * Immutable implementation of {@link DebitResponse}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code ImmutableDebitResponse.builder()}.
 */
@Generated(from = "DebitResponse", generator = "Immutables")
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated("org.immutables.processor.ProxyProcessor")
public final class ImmutableDebitResponse
    implements DebitResponse, Serializable {
  private final String providerRefNum;
  private final Date time;
  private final DebitStatus status;

  private ImmutableDebitResponse(
      String providerRefNum,
      Date time,
      DebitStatus status) {
    this.providerRefNum = providerRefNum;
    this.time = time;
    this.status = status;
  }

  /**
   * @return The value of the {@code providerRefNum} attribute
   */
  @JsonProperty("providerRefNum")
  @Override
  public String getProviderRefNum() {
    return providerRefNum;
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
  public DebitStatus getStatus() {
    return status;
  }

  /**
   * Copy the current immutable object by setting a value for the {@link DebitResponse#getProviderRefNum() providerRefNum} attribute.
   * An equals check used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for providerRefNum
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableDebitResponse withProviderRefNum(String value) {
    String newValue = Objects.requireNonNull(value, "providerRefNum");
    if (this.providerRefNum.equals(newValue)) return this;
    return new ImmutableDebitResponse(newValue, this.time, this.status);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link DebitResponse#getTime() time} attribute.
   * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for time
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableDebitResponse withTime(Date value) {
    if (this.time == value) return this;
    Date newValue = Objects.requireNonNull(value, "time");
    return new ImmutableDebitResponse(this.providerRefNum, newValue, this.status);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link DebitResponse#getStatus() status} attribute.
   * A value equality check is used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for status
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableDebitResponse withStatus(DebitStatus value) {
    if (this.status == value) return this;
    DebitStatus newValue = Objects.requireNonNull(value, "status");
    if (this.status.equals(newValue)) return this;
    return new ImmutableDebitResponse(this.providerRefNum, this.time, newValue);
  }

  /**
   * This instance is equal to all instances of {@code ImmutableDebitResponse} that have equal attribute values.
   * @return {@code true} if {@code this} is equal to {@code another} instance
   */
  @Override
  public boolean equals(Object another) {
    if (this == another) return true;
    return another instanceof ImmutableDebitResponse
        && equalTo((ImmutableDebitResponse) another);
  }

  private boolean equalTo(ImmutableDebitResponse another) {
    return providerRefNum.equals(another.providerRefNum)
        && time.equals(another.time)
        && status.equals(another.status);
  }

  /**
   * Computes a hash code from attributes: {@code providerRefNum}, {@code time}, {@code status}.
   * @return hashCode value
   */
  @Override
  public int hashCode() {
    int h = 5381;
    h += (h << 5) + providerRefNum.hashCode();
    h += (h << 5) + time.hashCode();
    h += (h << 5) + status.hashCode();
    return h;
  }

  /**
   * Prints the immutable value {@code DebitResponse} with attribute values.
   * @return A string representation of the value
   */
  @Override
  public String toString() {
    return MoreObjects.toStringHelper("DebitResponse")
        .omitNullValues()
        .add("providerRefNum", providerRefNum)
        .add("time", time)
        .add("status", status)
        .toString();
  }

  /**
   * Utility type used to correctly read immutable object from JSON representation.
   * @deprecated Do not use this type directly, it exists only for the <em>Jackson</em>-binding infrastructure
   */
  @Generated(from = "DebitResponse", generator = "Immutables")
  @Deprecated
  @JsonDeserialize
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
  static final class Json
      implements DebitResponse, Serializable {

    private static final long serialVersionUID = 202107072355659852L;
    String providerRefNum;
    Date time;
    DebitStatus status;
    @JsonProperty("providerRefNum")
    public void setProviderRefNum(String providerRefNum) {
      this.providerRefNum = providerRefNum;
    }
    @JsonProperty("time")
    public void setTime(Date time) {
      this.time = time;
    }
    @JsonProperty("status")
    public void setStatus(DebitStatus status) {
      this.status = status;
    }
    @Override
    public String getProviderRefNum() { throw new UnsupportedOperationException(); }
    @Override
    public Date getTime() { throw new UnsupportedOperationException(); }
    @Override
    public DebitStatus getStatus() { throw new UnsupportedOperationException(); }
  }

  /**
   * @param json A JSON-bindable data structure
   * @return An immutable value type
   * @deprecated Do not use this method directly, it exists only for the <em>Jackson</em>-binding infrastructure
   */
  @Deprecated
  @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
  static ImmutableDebitResponse fromJson(Json json) {
    ImmutableDebitResponse.Builder builder = ImmutableDebitResponse.builder();
    if (json.providerRefNum != null) {
      builder.providerRefNum(json.providerRefNum);
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
   * Creates an immutable copy of a {@link DebitResponse} value.
   * Uses accessors to get values to initialize the new immutable instance.
   * If an instance is already immutable, it is returned as is.
   * @param instance The instance to copy
   * @return A copied immutable DebitResponse instance
   */
  public static ImmutableDebitResponse copyOf(DebitResponse instance) {
    if (instance instanceof ImmutableDebitResponse) {
      return (ImmutableDebitResponse) instance;
    }
    return ImmutableDebitResponse.builder()
        .from(instance)
        .build();
  }

  private static final long serialVersionUID = 202107072355659852L;

  /**
   * Creates a builder for {@link ImmutableDebitResponse ImmutableDebitResponse}.
   * <pre>
   * ImmutableDebitResponse.builder()
   *    .providerRefNum(String) // required {@link DebitResponse#getProviderRefNum() providerRefNum}
   *    .time(Date) // required {@link DebitResponse#getTime() time}
   *    .status(rs.miromaric.plutus.provider.model.debit.DebitStatus) // required {@link DebitResponse#getStatus() status}
   *    .build();
   * </pre>
   * @return A new ImmutableDebitResponse builder
   */
  public static ImmutableDebitResponse.Builder builder() {
    return new ImmutableDebitResponse.Builder();
  }

  /**
   * Builds instances of type {@link ImmutableDebitResponse ImmutableDebitResponse}.
   * Initialize attributes and then invoke the {@link #build()} method to create an
   * immutable instance.
   * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
   * but instead used immediately to create instances.</em>
   */
  @Generated(from = "DebitResponse", generator = "Immutables")
  public static final class Builder {
    private static final long INIT_BIT_PROVIDER_REF_NUM = 0x1L;
    private static final long INIT_BIT_TIME = 0x2L;
    private static final long INIT_BIT_STATUS = 0x4L;
    private long initBits = 0x7L;

    private String providerRefNum;
    private Date time;
    private DebitStatus status;

    private Builder() {
    }

    /**
     * Fill a builder with attribute values from the provided {@code rs.miromaric.plutus.provider.model.debit.DebitResponse} instance.
     * @param instance The instance from which to copy values
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder from(DebitResponse instance) {
      Objects.requireNonNull(instance, "instance");
      from((Object) instance);
      return this;
    }

    /**
     * Fill a builder with attribute values from the provided {@code rs.miromaric.plutus.provider.model.PaymentResponse} instance.
     * @param instance The instance from which to copy values
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder from(PaymentResponse instance) {
      Objects.requireNonNull(instance, "instance");
      from((Object) instance);
      return this;
    }

    private void from(Object object) {
      if (object instanceof DebitResponse) {
        DebitResponse instance = (DebitResponse) object;
        status(instance.getStatus());
      }
      if (object instanceof PaymentResponse) {
        PaymentResponse instance = (PaymentResponse) object;
        providerRefNum(instance.getProviderRefNum());
        time(instance.getTime());
      }
    }

    /**
     * Initializes the value for the {@link DebitResponse#getProviderRefNum() providerRefNum} attribute.
     * @param providerRefNum The value for providerRefNum 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("providerRefNum")
    public final Builder providerRefNum(String providerRefNum) {
      this.providerRefNum = Objects.requireNonNull(providerRefNum, "providerRefNum");
      initBits &= ~INIT_BIT_PROVIDER_REF_NUM;
      return this;
    }

    /**
     * Initializes the value for the {@link DebitResponse#getTime() time} attribute.
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
     * Initializes the value for the {@link DebitResponse#getStatus() status} attribute.
     * @param status The value for status 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("status")
    public final Builder status(DebitStatus status) {
      this.status = Objects.requireNonNull(status, "status");
      initBits &= ~INIT_BIT_STATUS;
      return this;
    }

    /**
     * Builds a new {@link ImmutableDebitResponse ImmutableDebitResponse}.
     * @return An immutable instance of DebitResponse
     * @throws java.lang.IllegalStateException if any required attributes are missing
     */
    public ImmutableDebitResponse build() {
      if (initBits != 0) {
        throw new IllegalStateException(formatRequiredAttributesMessage());
      }
      return new ImmutableDebitResponse(providerRefNum, time, status);
    }

    private String formatRequiredAttributesMessage() {
      List<String> attributes = new ArrayList<>();
      if ((initBits & INIT_BIT_PROVIDER_REF_NUM) != 0) attributes.add("providerRefNum");
      if ((initBits & INIT_BIT_TIME) != 0) attributes.add("time");
      if ((initBits & INIT_BIT_STATUS) != 0) attributes.add("status");
      return "Cannot build DebitResponse, some of required attributes are not set " + attributes;
    }
  }
}
