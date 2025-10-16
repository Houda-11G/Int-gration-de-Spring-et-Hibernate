@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HibernateConfig.class)
@Transactional
public class ProductDaoTest {

    @Autowired
    private ProductDaoImpl productDao;

    @Test
    public void testCreateAndFind() {
        Product p = new Product();
        p.setName("Tablette");
        p.setPrice(500);
        productDao.create(p);

        Product retrieved = productDao.findById(p.getId());
        assertNotNull(retrieved);
        assertEquals("Tablette", retrieved.getName());
    }
}
