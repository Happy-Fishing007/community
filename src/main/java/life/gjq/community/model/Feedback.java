package life.gjq.community.model;

public class Feedback {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column feedback.id
     *
     * @mbg.generated Wed May 04 18:16:37 CST 2022
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column feedback.name
     *
     * @mbg.generated Wed May 04 18:16:37 CST 2022
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column feedback.contact
     *
     * @mbg.generated Wed May 04 18:16:37 CST 2022
     */
    private String contact;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column feedback.create_date
     *
     * @mbg.generated Wed May 04 18:16:37 CST 2022
     */
    private Long createDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column feedback.handle
     *
     * @mbg.generated Wed May 04 18:16:37 CST 2022
     */
    private String handle;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column feedback.content
     *
     * @mbg.generated Wed May 04 18:16:37 CST 2022
     */
    private String content;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column feedback.id
     *
     * @return the value of feedback.id
     *
     * @mbg.generated Wed May 04 18:16:37 CST 2022
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column feedback.id
     *
     * @param id the value for feedback.id
     *
     * @mbg.generated Wed May 04 18:16:37 CST 2022
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column feedback.name
     *
     * @return the value of feedback.name
     *
     * @mbg.generated Wed May 04 18:16:37 CST 2022
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column feedback.name
     *
     * @param name the value for feedback.name
     *
     * @mbg.generated Wed May 04 18:16:37 CST 2022
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column feedback.contact
     *
     * @return the value of feedback.contact
     *
     * @mbg.generated Wed May 04 18:16:37 CST 2022
     */
    public String getContact() {
        return contact;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column feedback.contact
     *
     * @param contact the value for feedback.contact
     *
     * @mbg.generated Wed May 04 18:16:37 CST 2022
     */
    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column feedback.create_date
     *
     * @return the value of feedback.create_date
     *
     * @mbg.generated Wed May 04 18:16:37 CST 2022
     */
    public Long getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column feedback.create_date
     *
     * @param createDate the value for feedback.create_date
     *
     * @mbg.generated Wed May 04 18:16:37 CST 2022
     */
    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column feedback.handle
     *
     * @return the value of feedback.handle
     *
     * @mbg.generated Wed May 04 18:16:37 CST 2022
     */
    public String getHandle() {
        return handle;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column feedback.handle
     *
     * @param handle the value for feedback.handle
     *
     * @mbg.generated Wed May 04 18:16:37 CST 2022
     */
    public void setHandle(String handle) {
        this.handle = handle == null ? null : handle.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column feedback.content
     *
     * @return the value of feedback.content
     *
     * @mbg.generated Wed May 04 18:16:37 CST 2022
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column feedback.content
     *
     * @param content the value for feedback.content
     *
     * @mbg.generated Wed May 04 18:16:37 CST 2022
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}