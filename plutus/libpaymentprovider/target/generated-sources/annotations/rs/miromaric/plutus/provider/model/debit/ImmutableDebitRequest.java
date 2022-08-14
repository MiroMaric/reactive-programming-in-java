package rs.miromaric.plutus.provider.model.debit;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.MoreObjects;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.immutables.value.Generated;
import rs.miromaric.plutus.provider.model.PaymentRequest;

/**
 * Immutable implementation of {@link DebitRequest}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code ImmutableDebitRequest.builder()}.
 */
@Generated(from = "DebitRequest", generator = "Immutables")
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated("org.immutables.processor.ProxyProcessor")
public final class ImmutableDebitRequest
    implements DebitRequest, Serializable {
  private final String accountId;
  private final String merchantRefNum;
  private final String token;
  private final BigDecimal amount;

  private ImmutableDebitRequest(
      String accountId,
      String merchantRefNum,
      String token,
      BigDecimal amount) {
    this.accountId = accountId;
    this.merchantRefNum = merchantRefNum;
    this.token = token;
    this.amount = amount;
  }

  /**
   * @return The value of the {@code accountId} attribute
   */
  @JsonProperty("accountId")
  @Override
  public String getAccountId() {
    return accountId;
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
   * @return The value of the {@code token} attribute
   */
  @JsonProperty("token")
  @Override
  public String getToken() {
    return token;
  }

  /**
   * @return The value of the {@code amount} attribute
   */
  @JsonProperty("amount")
  @Override
  public BigDecimal getAmount() {
    return amount;
  }

  /**
   * Copy the current immutable object by setting a value for the {@link DebitRequest#getAccountId() accountId} attribute.
   * An equals check used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for accountId
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableDebitRequest withAccountId(String value) {
    String newValue = Objects.requireNonNull(value, "accountId");
    if (this.accountId.equals(newValue)) return this;
    return new ImmutableDebitRequest(newValue, this.merchantRefNum, this.token, this.amount);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link DebitRequest#getMerchantRefNum() merchantRefNum} attribute.
   * An equals check used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for merchantRefNum
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableDebitRequest withMerchantRefNum(String value) {
    String newValue = Objects.requireNonNull(value, "merchantRefNum");
    if (this.merchantRefNum.equals(newValue)) return this;
    return new ImmutableDebitRequest(this.accountId, newValue, this.token, this.amount);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link DebitRequest#getToken() token} attribute.
   * An equals check used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for token
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableDebitRequest withToken(String value) {
    String newValue = Objects.requireNonNull(value, "token");
    if (this.token.equals(newValue)) return this;
    return new ImmutableDebitRequest(this.accountId, this.merchantRefNum, newValue, this.amount);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link DebitRequest#getAmount() amount} attribute.
   * An equals check used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for amount
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableDebitRequest withAmount(BigDecimal value) {
    BigDecimal newValue = Objects.requireNonNull(value, "amount");
    if (this.amount.equals(newValue)) return this;
    return new ImmutableDebitRequest(this.accountId, this.merchantRefNum, this.token, newValue);
  }

  /**
   * This instance is equal to all instances of {@code ImmutableDebitRequest} that have equal attribute values.
   * @return {@code true} if {@code this} is equal to {@code another} instance
   */
  @Override
  public boolean equals(Object another) {
    if (this == another) return true;
    return another instanceof ImmutableDebitRequest
        && equalTo((ImmutableDebitRequest) another);
  }

  private boolean equalTo(ImmutableDebitRequest another) {
    return accountId.equals(another.accountId)
        && merchantRefNum.equals(another.merchantRefNum)
        && token.equals(another.token)
        && amount.equals(another.amount);
  }

  /**
   * Computes a hash code from attributes: {@code accountId}, {@code merchantRefNum}, {@code token}, {@code amount}.
   * @return hashCode value
   */
  @Override
  public int hashCode() {
    int h = 5381;
    h += (h << 5) + accountId.hashCode();
    h += (h << 5) + merchantRefNum.hashCode();
    h += (h << 5) + token.hashCode();
    h += (h << 5) + amount.hashCode();
    return h;
  }

  /**
   * Prints the immutable value {@code DebitRequest} with attribute values.
   * @return A string representation of the value
   */
  @Override
  public String toString() {
    return MoreObjects.toStringHelper("DebitRequest")
        .omitNullValues()
        .add("accountId", accountId)
        .add("merchantRefNum", merchantRefNum)
        .add("token", token)
        .add("amount", amount)
        .toString();
  }

  /**
   * Utility type used to correctly read immutable object from JSON representation.
   * @deprecated Do not use this type directly, it exists only for the <em>Jackson</em>-binding infrastructure
   */
  @Generated(from = "DebitRequest", generator = "Immutables")
  @Deprecated
  @JsonDeserialize
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
  static final class Json
      implements DebitRequest, Serializable {

    private static final long serialVersionUID = 202107072355659852L;
    String accountId;
    String merchantRefNum;
    String token;
    BigDecimal amount;
    @JsonProperty("accountId")
    public void setAccountId(String accountId) {
      this.accountId = accountId;
    }
    @JsonProperty("merchantRefNum")
    public void setMerchantRefNum(String merchantRefNum) {
      this.merchantRefNum = merchantRefNum;
    }
    @JsonProperty("token")
    public void setToken(String token) {
      this.token = token;
    }
    @JsonProperty("amount")
    public void setAmount(BigDecimal amount) {
      this.amount = amount;
    }
    @Override
    public String getAccountId() { throw new UnsupportedOperationException(); }
    @Override
    public String getMerchantRefNum() { throw new UnsupportedOperationException(); }
    @Override
    public String getToken() { throw new UnsupportedOperationException(); }
    @Override
    public BigDecimal getAmount() { throw new UnsupportedOperationException(); }
  }

  /**
   * @param json A JSON-bindable data structure
   * @return An immutable value type
   * @deprecated Do not use this method directly, it exists only for the <em>Jackson</em>-binding infrastructure
   */
  @Deprecated
  @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
  static ImmutableDebitRequest fromJson(Json json) {
    ImmutableDebitRequest.Builder builder = ImmutableDebitRequest.builder();
    if (json.accountId != null) {
      builder.accountId(json.accountId);
    }
    if (json.merchantRefNum != null) {
      builder.merchantRefNum(json.merchantRefNum);
    }
    if (json.token != null) {
      builder.token(json.token);
    }
    if (json.amount != null) {
      builder.amount(json.amount);
    }
    return builder.build();
  }

  /**
   * Creates an immutable copy of a {@link DebitRequest} value.
   * Uses accessors to get values to initialize the new immutable instance.
   * If an instance is already immutable, it is returned as is.
   * @param instance The instance to copy
   * @return A copied immutable DebitRequest instance
   */
  public static ImmutableDebitRequest copyOf(DebitRequest instance) {
    if (instance instanceof ImmutableDebitRequest) {
      return (ImmutableDebitRequest) instance;
    }
    return ImmutableDebitRequest.builder()
        .from(instance)
        .build();
  }

  private static final long serialVersionUID = 202107072355659852L;

  /**
   * Creates a builder for {@link ImmutableDebitRequest ImmutableDebitRequest}.
   * <pre>
   * ImmutableDebitRequest.builder()
   *    .accountId(String) // required {@link DebitRequest#getAccountId() accountId}
   *    .merchantRefNum(String) // required {@link DebitRequest#getMerchantRefNum() merchantRefNum}
   *    .token(String) // required {@link DebitRequest#getToken() token}
   *    .amount(java.math.BigDecimal) // required {@link DebitRequest#getAmount() amount}
   *    .build();
   * </pre>
   * @return A new ImmutableDebitRequest builder
   */
  public static ImmutableDebitRequest.Builder builder() {
    return new ImmutableDebitRequest.Builder();
  }

  /**
   * Builds instances of type {@link ImmutableDebitRequest ImmutableDebitRequest}.
   * Initialize attributes and then invoke the {@link #build()} method to create an
   * immutable instance.
   * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
   * but instead used immediately to create instances.</em>
   */
  @Generated(from = "DebitRequest", generator = "Immutables")
  public static final class Builder {
    private static final long INIT_BIT_ACCOUNT_ID = 0x1L;
    private static final long INIT_BIT_MERCHANT_REF_NUM = 0x2L;
    private static final long INIT_BIT_TOKEN = 0x4L;
    private static final long INIT_BIT_AMOUNT = 0x8L;
    private long initBits = 0xfL;

    private String accountId;
    private String merchantRefNum;
    private String token;
    private BigDecimal amount;

    private Builder() {
    }

    /**
     * Fill a builder with attribute values from the provided {@code rs.miromaric.plutus.provider.model.PaymentRequest} instance.
     * @param instance The instance from which to copy values
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder from(PaymentRequest instance) {
      Objects.requireNonNull(instance, "instance");
      from((Object) instance);
      return this;
    }

    /**
     * Fill a builder with attribute values from the provided {@code rs.miromaric.plutus.provider.model.debit.DebitRequest} instance.
     * @param instance The instance from which to copy values
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder from(DebitRequest instance) {
      Objects.requireNonNull(instance, "instance");
      from((Object) instance);
      return this;
    }

    private void from(Object object) {
      if (object instanceof PaymentRequest) {
        PaymentRequest instance = (PaymentRequest) object;
        accountId(instance.getAccountId());
        amount(instance.getAmount());
        merchantRefNum(instance.getMerchantRefNum());
        token(instance.getToken());
      }
    }

    /**
     * Initializes the value for the {@link DebitRequest#getAccountId() accountId} attribute.
     * @param accountId The value for accountId 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("accountId")
    public final Builder accountId(String accountId) {
      this.accountId = Objects.requireNonNull(accountId, "accountId");
      initBits &= ~INIT_BIT_ACCOUNT_ID;
      return this;
    }

    /**
     * Initializes the value for the {@link DebitRequest#getMerchantRefNum() merchantRefNum} attribute.
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
     * Initializes the value for the {@link DebitRequest#getToken() token} attribute.
     * @param token The value for token 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("token")
    public final Builder token(String token) {
      this.token = Objects.requireNonNull(token, "token");
      initBits &= ~INIT_BIT_TOKEN;
      return this;
    }

    /**
     * Initializes the value for the {@link DebitRequest#getAmount() amount} attribute.
     * @param amount The value for amount 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("amount")
    public final Builder amount(BigDecimal amount) {
      this.amount = Objects.requireNonNull(amount, "amount");
      initBits &= ~INIT_BIT_AMOUNT;
      return this;
    }

    /**
     * Builds a new {@link ImmutableDebitRequest ImmutableDebitRequest}.
     * @return An immutable instance of DebitRequest
     * @throws java.lang.IllegalStateException if any required attributes are missing
     */
    public ImmutableDebitRequest build() {
      if (initBits != 0) {
        throw new IllegalStateException(formatRequiredAttributesMessage());
      }
      return new ImmutableDebitRequest(accountId, merchantRefNum, token, amount);
    }

    private String formatRequiredAttributesMessage() {
      List<String> attributes = new ArrayList<>();
      if ((initBits & INIT_BIT_ACCOUNT_ID) != 0) attributes.add("accountId");
      if ((initBits & INIT_BIT_MERCHANT_REF_NUM) != 0) attributes.add("merchantRefNum");
      if ((initBits & INIT_BIT_TOKEN) != 0) attributes.add("token");
      if ((initBits & INIT_BIT_AMOUNT) != 0) attributes.add("amount");
      return "Cannot build DebitRequest, some of required attributes are not set " + attributes;
    }
  }
}
