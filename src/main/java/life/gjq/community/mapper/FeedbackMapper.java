package life.gjq.community.mapper;

import java.util.List;
import life.gjq.community.model.Feedback;
import life.gjq.community.model.FeedbackExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface FeedbackMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table feedback
     *
     * @mbg.generated Wed May 04 18:16:37 CST 2022
     */
    long countByExample(FeedbackExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table feedback
     *
     * @mbg.generated Wed May 04 18:16:37 CST 2022
     */
    int deleteByExample(FeedbackExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table feedback
     *
     * @mbg.generated Wed May 04 18:16:37 CST 2022
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table feedback
     *
     * @mbg.generated Wed May 04 18:16:37 CST 2022
     */
    int insert(Feedback record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table feedback
     *
     * @mbg.generated Wed May 04 18:16:37 CST 2022
     */
    int insertSelective(Feedback record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table feedback
     *
     * @mbg.generated Wed May 04 18:16:37 CST 2022
     */
    List<Feedback> selectByExampleWithBLOBsWithRowbounds(FeedbackExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table feedback
     *
     * @mbg.generated Wed May 04 18:16:37 CST 2022
     */
    List<Feedback> selectByExampleWithBLOBs(FeedbackExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table feedback
     *
     * @mbg.generated Wed May 04 18:16:37 CST 2022
     */
    List<Feedback> selectByExampleWithRowbounds(FeedbackExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table feedback
     *
     * @mbg.generated Wed May 04 18:16:37 CST 2022
     */
    List<Feedback> selectByExample(FeedbackExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table feedback
     *
     * @mbg.generated Wed May 04 18:16:37 CST 2022
     */
    Feedback selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table feedback
     *
     * @mbg.generated Wed May 04 18:16:37 CST 2022
     */
    int updateByExampleSelective(@Param("record") Feedback record, @Param("example") FeedbackExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table feedback
     *
     * @mbg.generated Wed May 04 18:16:37 CST 2022
     */
    int updateByExampleWithBLOBs(@Param("record") Feedback record, @Param("example") FeedbackExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table feedback
     *
     * @mbg.generated Wed May 04 18:16:37 CST 2022
     */
    int updateByExample(@Param("record") Feedback record, @Param("example") FeedbackExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table feedback
     *
     * @mbg.generated Wed May 04 18:16:37 CST 2022
     */
    int updateByPrimaryKeySelective(Feedback record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table feedback
     *
     * @mbg.generated Wed May 04 18:16:37 CST 2022
     */
    int updateByPrimaryKeyWithBLOBs(Feedback record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table feedback
     *
     * @mbg.generated Wed May 04 18:16:37 CST 2022
     */
    int updateByPrimaryKey(Feedback record);
}